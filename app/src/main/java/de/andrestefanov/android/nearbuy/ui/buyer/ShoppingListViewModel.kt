package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api.data.ShoppingList
import de.andrestefanov.android.nearbuy.api.network.RestClient

class ShoppingListViewModel: ViewModel() {

    private var rest = RestClient.INSTANCE

    fun updateShopppingList() {
        ShoppingList.fromRequests(rest.getAcceptedRequests())
    }



}