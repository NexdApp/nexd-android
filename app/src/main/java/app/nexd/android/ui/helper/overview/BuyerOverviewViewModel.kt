package app.nexd.android.ui.helper.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import app.nexd.android.Preferences
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequest.StatusEnum.ONGOING
import app.nexd.android.api.model.HelpRequest.StatusEnum.PENDING
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject

class BuyerOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val reload = BehaviorSubject.create<Unit>()

    fun getMyAcceptedRequests(): LiveData<List<HelpRequest>> {
        val observable = api.helpListsControllerGetUserLists(userId = null)
            .map { helpLists ->
                return@map helpLists.filter { it.status == HelpList.StatusEnum.ACTIVE }
                    .maxBy { it.createdAt }
                    ?.helpRequests
                    .orEmpty()
            }

        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun getOtherOpenRequests(): LiveData<List<HelpRequest>> {
        val observable = reload.flatMap {
            api.userControllerFindMe().flatMap { me ->
                api.helpRequestsControllerGetAll(
                    null,
                    null,
                    null,
                    listOf(
                        PENDING.value,
                        ONGOING.value // TODO remove this line
                    )
                )
                    .map { requests ->
                        requests.filter { it.requesterId != me.id }
                    }
            }
        }
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun reloadData() {
        reload.onNext(Unit)
    }

}
