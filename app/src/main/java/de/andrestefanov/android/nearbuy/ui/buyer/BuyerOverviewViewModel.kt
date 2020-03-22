package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api.network.RestClient

class BuyerOverviewViewModel : ViewModel() {
    private val rest = RestClient()

    fun getNearbyOpenRequests() = MutableLiveData(rest.getNearbyOpenRequests())

    fun getAcceptedRequestItems(): Int {
        var totalItems = 0
        getAcceptedRequests().value?.forEach {
            for (item in it.items) {
                totalItems += item.amount
            }
        }
        return totalItems
    }

    fun getAcceptedRequests() = MutableLiveData(rest.getAcceptedRequests())


}
