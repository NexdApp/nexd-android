package app.nexd.android.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Preferences
import app.nexd.android.api
import app.nexd.android.api.model.RegisterPayload
import app.nexd.android.api.model.UpdateUserDto
import io.reactivex.android.schedulers.AndroidSchedulers

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    class RegisterViewState(val successful: Boolean, val error: Throwable? = null)

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): LiveData<RegisterViewState> {
        val registerState = MutableLiveData<RegisterViewState>()
        with(api) {
            authControllerRegister(
                RegisterPayload()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
            ).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ registerResponse ->
                    Preferences.setToken(getApplication(), registerResponse.accessToken)
                    Preferences.setUserId(getApplication(), registerResponse.id)
                    registerState.value = RegisterViewState(true)
                }, { t ->
                    registerState.value = RegisterViewState(false, t)
                })
        }
        return registerState
    }

}