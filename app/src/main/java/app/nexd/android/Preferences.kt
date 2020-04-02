package app.nexd.android

import android.content.Context

class Preferences {

    companion object {

        const val PREFS_NAME = "preferences"
        private const val TOKEN_KEY = "token"
        private const val USER_ID_KEY = "userId"

        private fun getPrefs(context: Context) = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        fun setToken(context: Context, token: String) {
            getPrefs(context)
                .edit().putString(TOKEN_KEY, token).apply()
        }

        fun getToken(context: Context) = getPrefs(
            context
        ).getString(TOKEN_KEY, null)

        fun removeToken(context: Context) {
            getPrefs(context)
                .edit().remove(TOKEN_KEY).apply()
        }

        fun setUserId(context: Context, userId: Int) {
            getPrefs(context)
                .edit().putInt(USER_ID_KEY, userId).apply()
        }

        fun getUserId(context: Context) = getPrefs(
            context
        ).getInt(USER_ID_KEY, -1)


        fun removeUserId(context: Context) {
            getPrefs(context)
                .edit().remove(USER_ID_KEY).apply()
        }

    }

}