package app.nexd.android.ui.seeker.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.api.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.processors.BehaviorProcessor

class SeekerCreateRequestViewModel(private val api: Api) : ViewModel() {

    enum class State {
        LOADING,
        IDLE,
        PROCESSING,
        FINISHED
    }

    private val state = BehaviorProcessor.createDefault(State.LOADING)
    private var requestToConfirm: HelpRequestCreateDto? = null

    val firstName = MutableLiveData("")
    val firstNameError = MutableLiveData<Int?>(null)

    val lastName = MutableLiveData("")
    val lastNameError = MutableLiveData<Int?>(null)

    val street = MutableLiveData("")
    val streetError = MutableLiveData<Int?>(null)

    val number = MutableLiveData("")
    val numberError = MutableLiveData<Int?>(null)

    val zipCode = MutableLiveData("")
    val zipCodeError = MutableLiveData<Int?>(null)

    val city = MutableLiveData("")
    val cityError = MutableLiveData<Int?>(null)

    val phoneNumber = MutableLiveData("")
    val phoneNumberError = MutableLiveData<Int?>(null)

    val additionalRequest = MutableLiveData<Int?>(null)

    internal fun sendRequest() {
        val success = firstName.hasValueOrSetError(firstNameError) &&
                lastName.hasValueOrSetError(lastNameError) &&
                street.hasValueOrSetError(streetError) &&
                number.hasValueOrSetError(numberError) &&
                zipCode.hasValueOrSetError(zipCodeError) &&
                city.hasValueOrSetError(cityError) &&
                phoneNumber.hasValueOrSetError(phoneNumberError)

        if (success) {
            with(api) {
                setRequestFromEditTextFields()
                helpRequestsControllerInsertRequestWithArticles(requestToConfirm)
                    .subscribe { state.onNext(State.FINISHED) }
            }
            requestToConfirm = null
        }
    }

    internal fun getCurrentUser(): LiveData<User> {
        return LiveDataReactiveStreams.fromPublisher(
            api.userControllerFindMe().toFlowable(BackpressureStrategy.LATEST)
        )
    }

    internal fun getArticles(): LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }


    private fun setRequestFromEditTextFields() {
        requestToConfirm!!.let {
            it.firstName = firstName.value
            it.lastName = lastName.value
            it.street = street.value
            it.number = number.value
            it.zipCode = zipCode.value
            it.city = city.value
            it.phoneNumber = phoneNumber.value
        }
    }

    fun state() = LiveDataReactiveStreams.fromPublisher(state)

    private fun MutableLiveData<String>.hasValueOrSetError(errorField: MutableLiveData<Int?>): Boolean {
        return if (this.value.isNullOrBlank()) {
            errorField.value = R.string.error_message_user_detail_field_missing
            false
        } else {
            errorField.value = null
            true
        }
    }

    internal fun setUserInfo() {
        getCurrentUser().let {
            firstName.value = it.value?.firstName ?: ""
            lastName.value = it.value?.firstName ?: ""
            street.value = it.value?.firstName ?: ""
            number.value = it.value?.firstName ?: ""
            zipCode.value = it.value?.firstName ?: ""
            city.value = it.value?.firstName ?: ""
            phoneNumber.value = it.value?.firstName ?: ""
        }
    }


    internal fun setRequestToConfirm(request: HelpRequestCreateDto) {
        requestToConfirm = request
        state.onNext(State.PROCESSING)
    }

    internal fun setStateToLoading() {
        state.onNext(State.LOADING)
    }
}
