package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.model.network.RestClient

class BuyerOverviewViewModel : ViewModel() {
    private val rest = RestClient()

    fun getNearbyOpenRequests() = MutableLiveData(rest.getNearbyOpenRequests())

    fun getAcceptedRequestItems(): Int {
        var totalItems = 0
        getAcceptedRequests().value?.forEach {
            totalItems += it.items.size
        }
        return totalItems
    }

    fun getAcceptedRequests() = MutableLiveData(rest.getAcceptedRequests())


}
