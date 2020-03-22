package de.andrestefanov.android.nearbuy.ui.seeker.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.ApiClient
import de.andrestefanov.android.nearbuy.api.data.NewHelpRequest
import de.andrestefanov.android.nearbuy.api.network.RestClient

class CreateHelpRequestViewModel : ViewModel() {

    private val rest = RestClient.INSTANCE

    fun getArticles(): LiveData<NewHelpRequest> {
        val data = rest.getArticles()
            .map { list ->
                NewHelpRequest(
                    list,
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            }
        return LiveDataReactiveStreams.fromPublisher(data.toFlowable())
    }

    fun sendRequest(request: NewHelpRequest) {
        rest.sendHelpRequest(request).subscribe()
    }

}
