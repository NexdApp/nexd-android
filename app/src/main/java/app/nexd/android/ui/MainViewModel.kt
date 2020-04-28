package app.nexd.android.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import app.nexd.android.NavGraphDirections
import app.nexd.android.Preferences

class MainViewModel(private val preferences: Preferences) : ViewModel() {

    private fun isAuthenticated() = !preferences.getToken().isNullOrBlank()

    fun getNavigationOverride(): NavDirections? {
        return if (isAuthenticated()) {
            if (preferences.registrationComplete) {
                Log.v("Navigation", "redirect to roleFragment")
                NavGraphDirections.toRoleFragmentOnAuth()
            } else {
                Log.v("Navigation", "redirect to registerDetailedViewModel")
                NavGraphDirections.toRegisterDetailedFragmentOnAuth()
            }
        } else null
    }
}