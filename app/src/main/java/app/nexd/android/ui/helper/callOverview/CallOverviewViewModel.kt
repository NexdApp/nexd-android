package app.nexd.android.ui.helper.callOverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.Call
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable

class CallOverviewViewModel : ViewModel() {

    sealed class Progress {
        object Idle: Progress()
        class Error(val message: String): Progress()
    }

    val progress = MutableLiveData<Progress>(Progress.Idle)

    fun getOpenCalls(): LiveData<List<Call>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.callsControllerCalls(
                null,
                "false",
                null,
                null,
                null
            )
                .onErrorReturn {
                    progress.value = Progress.Error(it.message.toString())
                    emptyList()
                }
                .toFlowable(BackpressureStrategy.BUFFER)
        )
    }

}