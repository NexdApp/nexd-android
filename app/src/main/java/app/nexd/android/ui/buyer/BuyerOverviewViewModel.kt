package app.nexd.android.ui.buyer

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.RequestEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class BuyerOverviewViewModel : ViewModel() {

    private val reload = BehaviorSubject.create<Unit>()

    fun getAllRequests(): LiveData<List<RequestEntity>> {

        val observable = reload.flatMap {
            api.requestControllerGetAll(
                null,
                null
            ).flatMapSingle { requests ->
                Observable.fromIterable(requests).flatMap { request ->
                    api.requestControllerGetSingleRequest(request.id.toBigDecimal())
                        .doOnNext {
                            request.requester = it.requester
                        }
                }.toList()
            }
        }

        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun reloadData() {
        reload.onNext(Unit)
    }

}
