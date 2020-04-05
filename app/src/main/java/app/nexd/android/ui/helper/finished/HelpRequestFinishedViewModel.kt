package app.nexd.android.ui.helper.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpRequest
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject

class HelpRequestFinishedViewModel: ViewModel() {

    private val reload = BehaviorSubject.createDefault(Unit)

    fun getFinishedRequests(): LiveData<List<HelpRequest>> {
        val observable = reload.flatMap {
            api.userControllerFindMe()
                .flatMap { me ->
                    api.helpRequestsControllerGetAll(
                        null,
                        me.id,
                        null,
                        "true",
                        listOf(
                            HelpRequest.StatusEnum.COMPLETED.value,
                            HelpRequest.StatusEnum.DEACTIVATED.value // TODO remove this line
                        )
                    )
                }
        }
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

}