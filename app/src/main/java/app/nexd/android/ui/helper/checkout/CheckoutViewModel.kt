package app.nexd.android.ui.helper.checkout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import io.reactivex.BackpressureStrategy

class CheckoutViewModel: ViewModel() {

    fun getAcceptedRequests() : LiveData<List<HelpList>> {
        val source = api.helpListsControllerGetUserLists(userId = null)
            .map { all -> all.filter { it.status == HelpList.StatusEnum.ACTIVE } }
            .doOnError {
                Log.e("Error", it.message.toString())
            }
            .onErrorReturnItem(emptyList())
            .toFlowable(BackpressureStrategy.BUFFER)

        return LiveDataReactiveStreams.fromPublisher(source)
    }

}