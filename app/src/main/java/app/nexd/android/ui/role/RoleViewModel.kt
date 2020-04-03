package app.nexd.android.ui.role

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import app.nexd.android.Preferences

class RoleViewModel(application: Application) : AndroidViewModel(application) {

    enum class Role {
        HELPER,
        REQUESTER
    }

    fun setRole(role: Role) {
        // TODO:
    }

    fun logout() {
        Preferences.removeToken(getApplication())
        Preferences.removeUserId(getApplication())
    }

}
