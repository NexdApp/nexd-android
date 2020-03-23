package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api.network.RestClient

class DeliveryViewModel: ViewModel() {

    private val rest = RestClient.INSTANCE

    fun getAcceptedRequests() = MutableLiveData(rest.getAcceptedRequests())

}