package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.model.network.RestClient

class BuyerOverviewViewModel : ViewModel() {
    private val rest = RestClient()

    fun getNearRequests() = MutableLiveData(rest.getNearbyOpenRequests())


}
