package app.nexd.android.ui.helper.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpRequest
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject

class HelpRequestFinishedViewModel: ViewModel() {

    private val reload = BehaviorSubject.createDefault(Unit)

    fun getFinishedRequests(): LiveData<List<HelpRequest>> {
        val observable = reload.flatMap {
            api.helpListsControllerGetUserLists(null)
                .map { helpLists ->
                    helpLists.filter {
                        it.status == HelpList.StatusEnum.COMPLETED
                    }
                        .maxBy { it.createdAt }?.helpRequests
                        .orEmpty()
                }
        }
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

}