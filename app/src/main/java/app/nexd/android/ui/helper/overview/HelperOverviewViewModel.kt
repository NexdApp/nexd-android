package app.nexd.android.ui.helper.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequest.StatusEnum.ONGOING
import app.nexd.android.api.model.HelpRequest.StatusEnum.PENDING
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject

class HelperOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val reload = BehaviorSubject.create<Unit>()

    // mb differnet way to get active requests??
    fun getMyAcceptedRequests(): LiveData<List<HelpRequest>> {
        val observable = reload.flatMap {
            api.helpListsControllerGetUserLists(null)
                .map { helpLists ->
                    helpLists.filter { it.status == HelpList.StatusEnum.ACTIVE }
                        .maxBy { it.createdAt }?.helpRequests
                        .orEmpty()
                }
        }
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun getOtherOpenRequests(): LiveData<List<HelpRequest>> {
        val observable = reload.flatMap {
            api.userControllerFindMe()
                .flatMap { me ->
                api.helpRequestsControllerGetAll(
                    null,
                    me.id,
                    null,
                    "true",
                    listOf(
                        PENDING.value,
                        ONGOING.value // TODO remove this line
                    )
                )
            }
        }
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun reloadData() {
        reload.onNext(Unit)
    }

}
