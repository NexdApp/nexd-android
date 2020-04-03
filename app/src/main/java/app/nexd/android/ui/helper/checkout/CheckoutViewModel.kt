package app.nexd.android.ui.helper.checkout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.RequestEntity
import io.reactivex.BackpressureStrategy

class CheckoutViewModel: ViewModel() {

    fun getAcceptedRequests() : LiveData<List<RequestEntity>> {
        val source = api.requestControllerGetAll(null, null)
            .map { all -> all.filter { it.status == RequestEntity.StatusEnum.ONGOING } }
            .doOnError {
                Log.e("Error", it.message.toString())
            }
            .onErrorReturnItem(emptyList())
            .toFlowable(BackpressureStrategy.BUFFER)

        return LiveDataReactiveStreams.fromPublisher(source)
    }

}