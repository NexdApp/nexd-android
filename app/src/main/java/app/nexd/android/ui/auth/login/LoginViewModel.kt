package app.nexd.android.ui.auth.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import app.nexd.android.Api
import app.nexd.android.Preferences
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.api.model.LoginDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.HttpException

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    sealed class Progress {
        object Idle : Progress()
        object Loading : Progress()
        class Error(val message: String) : Progress()
        object Finished : Progress()
    }

    val username = MutableLiveData("")

    val usernameError = MutableLiveData(0)

    val password = MutableLiveData("")

    val passwordError = MutableLiveData(0)

    val progress = MutableLiveData<Progress>(Progress.Idle)

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun login() {
        var correct = true

        if (username.value.isNullOrEmpty()) {
            usernameError.value = R.string.error_message_login_field_missing
            correct = false
        }
        if (password.value.isNullOrEmpty()) {
            passwordError.value = R.string.error_message_login_field_missing
            correct = false
        }

        if (correct) {
            val disposable = api.authControllerLogin(
                LoginDto()
                    .email(username.value)
                    .password(password.value)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { responseTokenDto ->
                        api.setBearerToken(responseTokenDto.accessToken)
                        with(getApplication<Application>().applicationContext) {
                            Preferences.setToken(this, responseTokenDto.accessToken)
                        }

                        progress.value = Progress.Finished
                    },
                    onError = { t ->
                        progress.value = Progress.Error(
                            if (t is HttpException) {
                                when (t.code()) {
                                    401 -> getApplication<Application>().applicationContext.getString(R.string.error_message_login_failed)
                                    else -> t.message()
                                }
                            } else {
                                t.message ?: "Unbekannter Fehler"
                            }
                        )
                    }
                )
            compositeDisposable.add(disposable)
        }
    }
}