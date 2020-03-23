package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api
import de.andrestefanov.android.nearbuy.api.model.RequestEntity
import io.reactivex.BackpressureStrategy

class CheckoutViewModel: ViewModel() {

    fun getAcceptedRequests() : LiveData<List<RequestEntity>> {
        val source = api.requestControllerGetAll(null, null)
            .map { all -> all.filter { it.status == RequestEntity.StatusEnum.ONGOING } }
            .toFlowable(BackpressureStrategy.BUFFER)

        return LiveDataReactiveStreams.fromPublisher(source)
    }

}