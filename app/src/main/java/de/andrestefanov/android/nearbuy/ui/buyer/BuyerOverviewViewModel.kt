package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api
import de.andrestefanov.android.nearbuy.api.model.RequestEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable

class BuyerOverviewViewModel : ViewModel() {

    fun getAllRequests(): LiveData<List<RequestEntity>> {

        val observable = api.requestControllerGetAll(
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

        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

}
