package app.nexd.android.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import app.nexd.android.NavGraphDirections
import app.nexd.android.Preferences
import app.nexd.android.R

class MainViewModel(private val preferences: Preferences) : ViewModel() {

    private fun isAuthenticated() = !preferences.getToken().isNullOrBlank()

    fun getNavigationOverride(destination: NavDestination): NavDirections? {

        var ret: NavDirections? = null
        if (isAuthenticated()) {
            if (!preferences.registrationComplete && destination.id != R.id.registerDetailedFragment) {
                Log.v("Navigation", "redirect toRegisterDetailedFragmentOnAuth")
                ret = NavGraphDirections.toRegisterDetailedFragmentOnAuth()
            } else if (destination.id == R.id.authFragment) {
                Log.v("Navigation", "toRoleFragmentOnAuth")
                ret = NavGraphDirections.toRoleFragmentOnAuth()
            }
        }
        return ret
    }
}