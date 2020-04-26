package app.nexd.android.ui

import androidx.lifecycle.ViewModel
import app.nexd.android.Preferences

class MainViewModel(private val preferences: Preferences) : ViewModel() {

    fun isAuthenticated() = !preferences.getToken().isNullOrBlank()

}