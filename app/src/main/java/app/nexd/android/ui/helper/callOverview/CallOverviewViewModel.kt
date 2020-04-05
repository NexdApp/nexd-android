package app.nexd.android.ui.helper.callOverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.Call
import io.reactivex.BackpressureStrategy

class CallOverviewViewModel : ViewModel() {

    fun getOpenCalls(): LiveData<List<Call>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.callsControllerCalls(
                null,
                null,
                null,
                null,
                null
            )
                .toFlowable(BackpressureStrategy.BUFFER)
        )
    }

}