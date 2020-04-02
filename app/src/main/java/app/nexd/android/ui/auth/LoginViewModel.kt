package app.nexd.android.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.nexd.android.Preferences
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.api.model.LoginPayload
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.HttpException

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    class LoginViewState(val successful: Boolean, val errorResponse: Throwable? = null)

    fun login(email: String, password: String): LiveData<LoginViewState> {
        val response = MutableLiveData<LoginViewState>()
        with(api) {
            authControllerLogin(LoginPayload()
                    .email(email)
                    .password(password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { loginResponse ->
                        response.value = LoginViewState(true)
                        Preferences.setToken(getApplication(), loginResponse.accessToken)
                        Preferences.setUserId(getApplication(), loginResponse.id)
                        api.setBearerToken(loginResponse.accessToken)
                    },
                    { t ->
                        response.value = LoginViewState(false, t)
                    }
                )
        }
        return response
    }

}