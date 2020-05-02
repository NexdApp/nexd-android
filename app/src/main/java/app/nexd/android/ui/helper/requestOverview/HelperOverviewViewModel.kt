package app.nexd.android.ui.helper.requestOverview

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequestStatus
import app.nexd.android.ui.common.Constants.USER_ME
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject

class HelperOverviewViewModel(application: Application, private val api: Api) : AndroidViewModel(application) {

    sealed class Progress {
        object Idle : Progress()
        object Loading : Progress()
        class ZipCodeDialog(val zipCode: String) : Progress()
        class Error(@StringRes val message: Int) : Progress()
    }

    // TODO: refactor to proper two way data binding

    val zipCode = BehaviorSubject.create<String>()

    val myAcceptedRequests: LiveData<List<HelpRequest>>

    val openRequests: LiveData<List<HelpRequest>>

    val progress = MutableLiveData<Progress>(Progress.Idle)

    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(
            api.userControllerFindMe()
                .map { it.zipCode ?: "" }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { zipCode.onNext(it) },
                    onError = { progress.value = Progress.Error(R.string.error_message_unknown) } // TODO: proper error handling
                )
        )

        val acceptedRequests =
            api.helpListsControllerGetUserLists(null)
                .map { helpLists ->
                    helpLists.filter { it.status == HelpList.StatusEnum.ACTIVE }
                        .maxBy { it.createdAt }?.helpRequests
                        .orEmpty()
                }

        val openRequests = zipCode.flatMap { zip ->
            api.helpRequestsControllerGetAll(
                USER_ME,
                true,
                listOf(zip),
                false,
                listOf(HelpRequestStatus.PENDING)
            )
        }

        this.openRequests = LiveDataReactiveStreams.fromPublisher(
            openRequests.toFlowable(BackpressureStrategy.BUFFER)
        )

        this.myAcceptedRequests = LiveDataReactiveStreams.fromPublisher(
            acceptedRequests.toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }

    fun editZipCode() {
        progress.value = Progress.ZipCodeDialog(zipCode.value ?: "")
    }

    fun filterByZipCode(zipCode: String) {
        this.zipCode.onNext(zipCode)
    }
}
