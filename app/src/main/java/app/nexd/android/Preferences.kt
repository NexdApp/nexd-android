package app.nexd.android

import android.content.Context
import androidx.core.content.edit

class Preferences(context: Context) {

    companion object {

        private const val PREFS_NAME = "preferences"
        private const val TOKEN_KEY = "token"
        private const val REGISTRATION_COMPLETE_KEY = "registration_complete"

    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setToken(token: String?) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken() = prefs.getString(TOKEN_KEY, null)

    var registrationComplete: Boolean
        get() = prefs.getBoolean(REGISTRATION_COMPLETE_KEY, false)
        set(value) = prefs.edit { putBoolean(REGISTRATION_COMPLETE_KEY, value) }

}