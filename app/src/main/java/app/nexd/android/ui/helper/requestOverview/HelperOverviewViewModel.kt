package app.nexd.android.ui.helper.requestOverview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequest.StatusEnum.PENDING
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject

class HelperOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val reloadAccepted = BehaviorSubject.create<Unit>()
    private val reloadNearby = BehaviorSubject.create<Unit>()

    var zipCode: String? = null
        private set

    // mb different way to get active requests??
    fun getMyAcceptedRequests(): LiveData<List<HelpRequest>> {
        val observable = reloadAccepted.flatMap {
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
        val observable =
            api.userControllerFindMe()
                .flatMap { currentUser ->
                    zipCode = currentUser.zipCode
                    reloadNearby.flatMap { // get all requests created by other people
                        api.helpRequestsControllerGetAll(
                            "me",
                            true,
                            listOf(zipCode, ""),
                            true,
                            listOf(
                                PENDING.value
                            )
                        )
                            .map { list -> list.filter { it.helpListId == null } } // TODO shouldn't be needed

                    }
                }
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun reloadData() {
        reloadAccepted.onNext(Unit)
        reloadNearby.onNext(Unit)
    }

    fun filterbyZipCode(zipCode: String) {
        this.zipCode = zipCode
        reloadNearby.onNext(Unit)
    }
}
