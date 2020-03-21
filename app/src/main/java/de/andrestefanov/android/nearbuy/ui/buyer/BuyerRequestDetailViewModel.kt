package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.model.network.RestClient

class BuyerRequestDetailViewModel(request: HelpRequest): ViewModel() {

    var request: MutableLiveData<HelpRequest> = MutableLiveData(request)

    class BuyerRequestDetailViewModelFactory(private var request: HelpRequest)
        : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BuyerRequestDetailViewModel(request) as T
        }

    }
}