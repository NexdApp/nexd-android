package app.nexd.android.ui.role

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import app.nexd.android.Preferences
import app.nexd.android.api
import app.nexd.android.api.model.User
import io.reactivex.BackpressureStrategy

class RoleViewModel(application: Application) : AndroidViewModel(application) {

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
