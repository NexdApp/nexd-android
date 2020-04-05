package app.nexd.android.ui.seeker.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpRequest
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable

class SeekerDetailViewModel: ViewModel() {

    sealed class Progress {
        object Idle : Progress()
        object Error : Progress()
        object Removed : Progress()
    }

    val progress = MutableLiveData<Progress>(Progress.Idle)

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getRequest(id: Int): LiveData<HelpRequest> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpRequestsControllerGetSingleRequest(id)
                .onErrorReturnItem(HelpRequest()) // TODO return state
                .toFlowable(BackpressureStrategy.LATEST))
    }

    fun cancelRequest(request: HelpRequest) {
        // TODO: cancel/remove own request
    }

}