package app.nexd.android.ui.seeker.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpRequest
import io.reactivex.BackpressureStrategy

class SeekerDetailViewModel: ViewModel() {

    fun getRequest(id: Long): LiveData<HelpRequest> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpRequestsControllerGetSingleRequest(id.toBigDecimal())
                .onErrorReturnItem(HelpRequest()) // TODO return state
                .toFlowable(BackpressureStrategy.LATEST))
    }

    fun cancelRequest(requestId: Long) {
        // api.
        // api.request
    }

}