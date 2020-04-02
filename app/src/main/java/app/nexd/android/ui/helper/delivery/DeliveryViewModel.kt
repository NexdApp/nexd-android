package app.nexd.android.ui.helper.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.RequestEntity
import io.reactivex.BackpressureStrategy

class DeliveryViewModel: ViewModel() {

    fun getAcceptedRequests() : LiveData<List<RequestEntity>> {
        val source = api.requestControllerGetAll(null, null)
            .map { all -> all.filter { it.status == RequestEntity.StatusEnum.ONGOING } }
            .toFlowable(BackpressureStrategy.BUFFER)

        return LiveDataReactiveStreams.fromPublisher(source)
    }

}