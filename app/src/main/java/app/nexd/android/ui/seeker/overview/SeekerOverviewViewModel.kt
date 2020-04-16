package app.nexd.android.ui.seeker.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequestStatus.*
import io.reactivex.BackpressureStrategy

class SeekerOverviewViewModel : ViewModel() {

    fun getHelpRequests(): LiveData<List<HelpRequest>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpRequestsControllerGetAll(
                "me",
                false,
                null,
                false,
                listOf(
                    PENDING,
                    ONGOING
                )
            )
                .toFlowable(BackpressureStrategy.BUFFER)
        )
    }
}
