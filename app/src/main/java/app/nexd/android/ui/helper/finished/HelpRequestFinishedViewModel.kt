package app.nexd.android.ui.helper.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpRequest
import io.reactivex.BackpressureStrategy

class HelpRequestFinishedViewModel: ViewModel() {

    fun getFinishedRequests(): LiveData<List<HelpRequest>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpListsControllerGetUserLists(null)
                .map { helpLists ->
                    helpLists.filter {
                        it.status == HelpList.StatusEnum.COMPLETED
                    }
                        .maxBy { it.createdAt }?.helpRequests
                        .orEmpty()
                }
                .toFlowable(BackpressureStrategy.BUFFER)
        )
    }

}