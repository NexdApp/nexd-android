package app.nexd.android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.Preferences
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers

enum class AuthState {
    UNAUTHENTICATED,
    REGISTERED,
    INCOMPLETE,
    COMPLETE
}

class MainViewModel(
    private val api: Api,
    private val preferences: Preferences) : ViewModel() {


    private val token = MutableLiveData<String?>(preferences.getToken())

    private val _detailsAdded = MutableLiveData<Boolean>()


    private val _authState = MediatorLiveData<AuthState>().apply {
        value = AuthState.UNAUTHENTICATED

        addSource(token) {
            when (it) {
                null, "" -> AuthState.UNAUTHENTICATED
                else -> {
                    value = AuthState.REGISTERED
                    api.userControllerFindMe()
                        .toFlowable(BackpressureStrategy.LATEST)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { user ->
                            val hasEmptyDetail = with(user) {
                                phoneNumber.isNullOrEmpty()
                                        || street.isNullOrEmpty()
                                        || number.isNullOrEmpty()
                                        || zipCode.isNullOrEmpty()
                                        || city.isNullOrEmpty()
                            }
                            if (hasEmptyDetail) {
                                value = AuthState.INCOMPLETE
                                Log.d("auth", "incomplete")
                            }
                        }
                }
            }
        }
        addSource(_detailsAdded) {
            value = AuthState.COMPLETE
        }
    }
    val authState: LiveData<AuthState> = run {
        Log.d("auth state flow", "${_authState.value}")
        _authState }

    fun authenticate(token: String) {
        preferences.setToken(token) // for API use
        this.token.value = token

    }

    fun logout() {
        preferences.setToken("")
        this.token.value = null
    }

    fun setUserAsComplete() {
        _detailsAdded.value = true
    }

}