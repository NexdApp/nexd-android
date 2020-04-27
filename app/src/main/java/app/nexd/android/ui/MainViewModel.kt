package app.nexd.android.ui

import androidx.lifecycle.ViewModel
import app.nexd.android.Preferences
import android.util.Log
import androidx.annotation.IdRes
import app.nexd.android.R

class MainViewModel(private val preferences: Preferences) : ViewModel() {

    private fun isAuthenticated() = !preferences.getToken().isNullOrBlank()

    @IdRes
    fun getNavigationDestination(): Int? {
        return if (isAuthenticated()) {
            if (preferences.registrationComplete) {
                Log.v("Navigation", "redirect to roleFragment")
                R.id.roleFragment
            } else {
                Log.v("Navigation", "redirect to registerDetailedViewModel")
                R.id.registerDetailedFragment
            }
        } else null
    }
}