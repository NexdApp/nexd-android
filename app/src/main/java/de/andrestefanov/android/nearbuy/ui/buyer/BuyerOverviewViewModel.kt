package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api
import de.andrestefanov.android.nearbuy.api.model.RequestEntity
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
