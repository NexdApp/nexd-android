package app.nexd.android.ui.seeker.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequestCreateDto
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class SeekerDetailViewModel: ViewModel() {

    sealed class Progress {
        object Idle : Progress()
        object Error : Progress()
        object Canceled : Progress()
    }

    val progress = MutableLiveData<Progress>(Progress.Idle)

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getRequest(id: Long): LiveData<HelpRequest> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpRequestsControllerGetSingleRequest(id)
                .onErrorReturnItem(HelpRequest()) // TODO: error handling
                .toFlowable(BackpressureStrategy.LATEST))
    }

    fun cancelRequest(request: HelpRequest) {
        with(api) {
            helpRequestsControllerUpdateRequest(
                request.id ?: 0,
                HelpRequestCreateDto()
                    .status(HelpRequestCreateDto.StatusEnum.DEACTIVATED)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    progress.value = Progress.Error
                }
                .subscribe {
                    progress.value = Progress.Canceled
                }
        }
    }

}