package de.andrestefanov.android.nearbuy.ui.seeker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api.network.RestClient

class SeekerOverviewViewModel : ViewModel() {

    private val rest = RestClient()

    fun getHelpRequests() = MutableLiveData(rest.getMyRequests())

}
