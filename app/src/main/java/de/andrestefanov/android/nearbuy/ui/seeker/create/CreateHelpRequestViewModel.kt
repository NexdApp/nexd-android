package de.andrestefanov.android.nearbuy.ui.seeker.create

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api.network.RestClient

class CreateHelpRequestViewModel : ViewModel() {

    private val rest = RestClient()

    fun getArticles() = LiveDataReactiveStreams.fromPublisher(rest.getArticles().toFlowable())

}
