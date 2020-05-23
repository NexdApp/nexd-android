package app.nexd.android.ui.role

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.Preferences
import app.nexd.android.api.model.User
import io.reactivex.BackpressureStrategy

class RoleViewModel(private val api: Api, private val preferences: Preferences) : ViewModel() {

    fun getMe(): LiveData<User> {
        return LiveDataReactiveStreams.fromPublisher(
            api.userControllerFindMe()
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

}
