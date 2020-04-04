package app.nexd.android.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.nexd.android.Preferences
import app.nexd.android.api
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    class LoginViewState(val successful: Boolean, val errorResponse: Throwable? = null)

    fun login(email: String, password: String): LiveData<LoginViewState> {
        val response = MutableLiveData<LoginViewState>()
        with(api) {
            authControllerLogin()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { loginResponse ->
                        response.value = LoginViewState(true)
                        Preferences.setToken(getApplication(), loginResponse.accessToken)

                        // TODO: save user and used id?

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