package app.nexd.android.ui.role

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.Preferences
import app.nexd.android.api
import app.nexd.android.api.model.User
import io.reactivex.BackpressureStrategy

class RoleViewModel(application: Application) : AndroidViewModel(application) {

    enum class Role {
        HELPER,
        REQUESTER
    }

    fun setRole(role: Role) {
        // TODO:
    }

    fun getMe(): LiveData<User> {
        return LiveDataReactiveStreams.fromPublisher(
            api.userControllerFindMe()
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun logout() {
        Preferences.removeToken(getApplication())
        // Preferences.removeUserId(getApplication())
    }

}
