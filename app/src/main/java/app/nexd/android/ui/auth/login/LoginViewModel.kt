package app.nexd.android.ui.auth.login

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.Preferences
import app.nexd.android.R
import app.nexd.android.api.model.LoginDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.HttpException

class LoginViewModel(
    private val preferences: Preferences,
    private val api: Api
) : ViewModel() {

    sealed class Progress {
        object Idle : Progress()
        object Loading : Progress()
        class Error(@StringRes val message: Int) : Progress()
        class Finished(val token: String) : Progress()
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
            progress.value = Progress.Loading
            val disposable = api.authControllerLogin(
                LoginDto()
                    .email(username.value)
                    .password(password.value)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { responseTokenDto ->
                        progress.value = Progress.Finished(token = responseTokenDto.accessToken)
                    },
                    onError = { t ->
                        progress.value = Progress.Error(
                            if (t is HttpException) {
                                when (t.code()) {
                                    401 -> R.string.error_message_login_failed
                                    else -> R.string.error_message_unknown
                                }
                            } else {
                                R.string.error_message_unknown
                            }
                        )
                    }
                )
            compositeDisposable.add(disposable)
        }
    }
}