package app.nexd.android.ui.seeker.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequestCreateDto
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class SeekerDetailViewModel(private val api: Api) : ViewModel() {

    sealed class Progress {
        object Idle : Progress()
        object Error : Progress()
        object Canceled : Progress()
    }

    val progress = MutableLiveData<Progress>(Progress.Idle)

    val additionalRequest = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val street = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val zipCode = MutableLiveData<String>()
    val city = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getRequest(id: Long): LiveData<HelpRequest> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpRequestsControllerGetSingleRequest(id)
                .onErrorReturnItem(HelpRequest()) // TODO: error handling
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun cancelRequest(request: HelpRequest) {
        with(api) {
            helpRequestsControllerUpdateRequest(
                request.id ?: 0,
                HelpRequestCreateDto()
                    .status(HelpRequestCreateDto.StatusEnum.DEACTIVATED)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        progress.value = Progress.Canceled
                    },
                    {
                        progress.value = Progress.Error
                    }
                )

        }
    }

    fun setInfo(requestId: Long) {
        val observable = api.helpRequestsControllerGetSingleRequest(requestId)

        val disposable = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    firstName.value = it.firstName
                    lastName.value = it.lastName
                    street.value = it.street
                    number.value = it.number
                    zipCode.value = it.zipCode
                    city.value = it.city
                    additionalRequest.value = it.additionalRequest
                }
            )

        compositeDisposable.add(disposable)
    }

}