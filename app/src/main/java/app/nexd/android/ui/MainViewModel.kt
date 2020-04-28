package app.nexd.android.ui

import android.util.Log
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import app.nexd.android.Preferences
import app.nexd.android.R

class MainViewModel(private val preferences: Preferences) : ViewModel() {

    private fun isAuthenticated() = !preferences.getToken().isNullOrBlank()

    @IdRes
    fun getNavigationDestination(): Int? {
        if (isAuthenticated()) {
            if (!preferences.registrationComplete) {
                Log.v("Navigation", "redirect to registerDetailedViewModel")
                return R.id.registerDetailedFragment
            }
        }

        return null
    }
}