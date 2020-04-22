package app.nexd.android

import android.content.Context

class Preferences(context: Context) {

    companion object {

        private const val PREFS_NAME = "preferences"
        private const val TOKEN_KEY = "token"

    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken() = prefs.getString(TOKEN_KEY, null)

    fun removeToken() = prefs.edit().remove(TOKEN_KEY).apply()

}