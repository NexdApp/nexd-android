package app.nexd.android.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import app.nexd.android.Api
import app.nexd.android.NavGraphDirections
import app.nexd.android.Preferences
import app.nexd.android.R
import app.nexd.android.api.model.User
import io.reactivex.BackpressureStrategy

enum class AuthState {
    UNAUTHENTICATED,
    REGISTERED,
    COMPLETE
}

class MainViewModel(
    private val api: Api,
    private val preferences: Preferences) : ViewModel() {



    private fun isAuthenticated() = !preferences.getToken().isNullOrBlank()

    fun getNavigationOverride(destination: NavDestination): NavDirections? {

        var ret: NavDirections? = null
        if (isAuthenticated()) {
            if (!preferences.registrationComplete && destination.id != R.id.registerDetailedFragment) {
                Log.v("Navigation", "redirect toRegisterDetailedFragmentOnAuth")
                ret = NavGraphDirections.toRegisterDetailedFragmentOnAuth()
            } else if (destination.id == R.id.authFragment) {
                Log.v("Navigation", "toRoleFragmentOnAuth")
                ret = NavGraphDirections.toRoleFragmentOnAuth()
            }
        }
        return ret
    }

    private val token = MutableLiveData<String>()

    private val userRefreshTrigger = MutableLiveData<Boolean>()
    val user: LiveData<User> = run {
        val userMediator = MediatorLiveData<User>()
        userMediator.addSource(token) {
            userMediator.value =
               LiveDataReactiveStreams.fromPublisher(
                   api.userControllerFindMe()
                       .toFlowable(BackpressureStrategy.LATEST)
               ).value
        }
        userMediator.addSource(userRefreshTrigger) {
            userMediator.value =
                LiveDataReactiveStreams.fromPublisher(
                    api.userControllerFindMe()
                        .toFlowable(BackpressureStrategy.LATEST)
                ).value
        }
        userMediator
    }


    private val _authState = run {
        val authMediator = MediatorLiveData<AuthState>()
        authMediator.postValue(AuthState.UNAUTHENTICATED)

        authMediator.addSource(user) { user ->
            Log.d("user mediatior", "user triggered with value $user")
            user?.let {
                val hasEmptyDetail = with(user) {
                    phoneNumber.isNullOrEmpty()
                        || street.isNullOrEmpty()
                        || number.isNullOrEmpty()
                        || zipCode.isNullOrEmpty()
                        || city.isNullOrEmpty()
                }
                authMediator.value = when (hasEmptyDetail) {
                    true -> AuthState.REGISTERED
                    false -> AuthState.COMPLETE
                }
            }
        }
        authMediator
    }
    val authState: LiveData<AuthState> = _authState

    fun authenticateWithToken(token: String) {
        preferences.setToken(token) // for API use
        this.token.value = token
    }

    fun logout() {
        preferences.setToken("")
        this.token.value = null
    }

    fun refreshUser() {
        userRefreshTrigger.value = true

    }

}