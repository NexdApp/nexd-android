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
import app.nexd.android.api.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject

class HelperOverviewViewModel(application: Application) : AndroidViewModel(application) {

    class AvailableRequestWrapper(val requester: User, val type: RequestType, val id: Long)
    enum class RequestType {
        SHOPPING,
        TRANSCRIPT
    }

    private val reload = BehaviorSubject.create<Unit>()

    // mb different way to get active requests??
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

    fun getOtherOpenRequests(): LiveData<List<AvailableRequestWrapper>> {
        val observable = reload.flatMap {
            api.helpRequestsControllerGetAll(
                userId = null,
                excludeUserId = "me",
                zipCode = null,
                includeRequester = "true", // TODO this has to be boolean
                status = listOf(
                    PENDING.value,
                    ONGOING.value // TODO remove this line
                )
            )
            api.userControllerFindMe()
                .flatMap { me ->
                    api.helpRequestsControllerGetAll(
                        null,
                        null,
                        "true",
                        listOf(
                            PENDING.value
                        )
                    )
                        .map { requests ->
                            requests.filter { it.requesterId != me.id &&
                                    it.helpListId == null // not assigned to a shopping list yet
                            }
                        }

                        .flatMapIterable { requests ->
                            val requestWrapperList = mutableListOf<AvailableRequestWrapper>()
                            requests.forEach { request ->
                                requestWrapperList.add(
                                    AvailableRequestWrapper(
                                        request.requester ?: User(),
                                        RequestType.SHOPPING,
                                        request.id ?: 0
                                    )
                                )
                            }
                            requestWrapperList.add(
                                AvailableRequestWrapper(
                                    User(),
                                    RequestType.TRANSCRIPT,
                                    0
                                )
                            )
                            return@flatMapIterable requestWrapperList
                        }
                        .toList()
                        .toObservable()
                }
        }

        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun reloadData() {
        reload.onNext(Unit)
    }

}
