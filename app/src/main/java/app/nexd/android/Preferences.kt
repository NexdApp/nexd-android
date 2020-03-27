package app.nexd.android

import android.content.Context

class Preferences {

    companion object {

        const val PREFS_NAME = "preferences"

        private fun getPrefs(context: Context) = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        fun setToken(context: Context, token: String) {
            getPrefs(context)
                .edit().putString("token", token).apply()
        }

        fun getToken(context: Context) = getPrefs(
            context
        ).getString("token", null)

        fun setUserId(context: Context, userId: Int) {
            getPrefs(context)
                .edit().putInt("userId", userId).apply()
        }

        fun getUserId(context: Context) = getPrefs(
            context
        ).getInt("userId", -1)

    }

}