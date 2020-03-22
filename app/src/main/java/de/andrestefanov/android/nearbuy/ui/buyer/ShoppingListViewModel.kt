package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api.network.RestClient

class ShoppingListViewModel: ViewModel() {

    private var rest = RestClient.INSTANCE

    class ShoppingListEntry(var name: String, var amount: Int) {
        var collected = false
    }

    fun getItems(): MutableLiveData<List<ShoppingListEntry>> {
        val items: MutableList<ShoppingListEntry> = mutableListOf()

        for (request in rest.getAcceptedRequests()) {
            loop@ for (item in request.items) {
                for (shoppinglistEntry in items) {
                    if (shoppinglistEntry.name == item.name) {
                        shoppinglistEntry.amount += item.amount
                        continue@loop
                    }
                }
                items.add(ShoppingListEntry(item.name, item.amount))
            }
        }

        return MutableLiveData(items)
    }



}