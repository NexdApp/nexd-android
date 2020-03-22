package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api.network.RestClient

class CheckoutViewModel: ViewModel() {

    private val rest = RestClient.INSTANCE

    fun getAcceptedRequests() = MutableLiveData(rest.getAcceptedRequests())

}