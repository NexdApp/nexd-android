package app.nexd.android.ui.auth.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.Preferences
import app.nexd.android.api.model.UpdateUserDto
import app.nexd.android.ui.auth.register.model.Effect
import app.nexd.android.ui.auth.register.model.ErrorState
import app.nexd.android.ui.auth.register.model.Event
import app.nexd.android.ui.auth.register.model.Loading
import app.nexd.android.ui.auth.register.model.ValidationError
import app.nexd.android.ui.auth.register.model.ViewState
import app.nexd.android.ui.utils.Either
import app.nexd.android.ui.utils.SingleLiveEvent
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class RegisterDetailedViewModel(
    private val api: Api,
    private val preferences: Preferences,
    private val inputValidator: InputValidator,
    private val errorTranslator: ErrorTranslator
) : ViewModel() {

    val viewState: LiveData<ViewState>
        get() = mutableViewState
    private var mutableViewState = MutableLiveData<ViewState>(ViewState.init())

    val effects: LiveData<Effect>
        @Suppress("UNCHECKED_CAST") //this cast is safe SingleLiveEvent is extending LiveData
        get() = mutableEffects as LiveData<Effect>
    private var mutableEffects = SingleLiveEvent<Effect>()

    private val disposable = CompositeDisposable()

    fun event(event: Event) {
        Log.d(this.javaClass.simpleName, "event: $event")
        when (event) {
            is Event.UserRegistrationEvent -> tryRegisterUser(event)
        }
    }

    private fun tryRegisterUser(event: Event.UserRegistrationEvent) {
        val validationErrors = inputValidator.validate(event)
        if (validationErrors.isEmpty()) {
            registerUser(event)
        } else {
            showValidationErrors(validationErrors)
        }
    }

    private fun registerUser(event: Event.UserRegistrationEvent) {
        showLoading()
        Single.just(event)
            .map { it.toUserUpdateDto() }
            .flatMap { api.userControllerUpdateMyself(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { completeRegistration() },
                onError = { error -> handleRegistrationError(error) }
            ).addTo(disposable)
    }

    private fun updateViewState(viewState: ViewState?) {
        Log.d(this.javaClass.simpleName, "viewState: $viewState")
        mutableViewState.value = viewState
    }

    private fun handleRegistrationError(throwable: Throwable) {
        hideLoading()
        val error = errorTranslator.translate(throwable)
        when (error) {
            is Either.Left -> mutableEffects.value = Effect.ShowErrorMessage(error.value.message)
            is Either.Right -> updateViewState(mutableViewState.value?.copy(errorState = error.value))
        }
    }

    private fun completeRegistration() {
        hideLoading()
        preferences.registrationComplete = true
    }

    private fun showValidationErrors(validationErrors: List<ValidationError>) {
        mutableViewState.value = mutableViewState.value?.copy(errorState = ErrorState.FormValidationErrors(validationErrors), loading = Loading.NotLoading)
    }

    private fun hideLoading() {
        mutableViewState.value = ViewState(loading = Loading.NotLoading, errorState = ErrorState.NoError)
    }

    private fun showLoading() {
        updateViewState(mutableViewState.value?.copy(loading = Loading.IsLoading, errorState = ErrorState.NoError))
    }

    private fun Event.UserRegistrationEvent.toUserUpdateDto(): UpdateUserDto {
        return UpdateUserDto()
            .phoneNumber(phone)
            .street(street)
            .number(houseNumber)
            .zipCode(zip)
            .city(locality)
    }

    override fun onCleared() {
        disposable.clear()
    }
}