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
    val firstNameError = MutableLiveData(0)

    val lastName = MutableLiveData("")
    val lastNameError = MutableLiveData(0)

    val street = MutableLiveData("")
    val streetError = MutableLiveData(0)

    val number = MutableLiveData("")
    val numberError = MutableLiveData(0)

    val zipCode = MutableLiveData("")
    val zipCodeError = MutableLiveData(0)

    val city = MutableLiveData("")
    val cityError = MutableLiveData(0)

    val phoneNumber = MutableLiveData("")
    val phoneNumberError = MutableLiveData(0)

    val additionalRequest = MutableLiveData("")

    internal fun sendRequest() {
        val success = firstName.errorCheck(firstNameError) &&
                lastName.errorCheck(lastNameError) &&
                street.errorCheck(streetError) &&
                number.errorCheck(numberError) &&
                zipCode.errorCheck(zipCodeError) &&
                city.errorCheck(cityError) &&
                phoneNumber.errorCheck(phoneNumberError)

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

    internal fun setAddressTextFieldsFromCurrentUser() {
        requestToConfirm.let {
            // in case the value isNullOrBlank, it will be set to the parameter
            // parameter: 1. saved value from current request, 2. user's info, 3. ""
            firstName.value = firstName.setValueIfNullOrBlank(
                it?.firstName ?: getCurrentUser().value?.firstName ?: ""
            )
            lastName.value = lastName.setValueIfNullOrBlank(
                it?.lastName ?: getCurrentUser().value?.lastName ?: ""
            )
            street.value =
                street.setValueIfNullOrBlank(it?.street ?: getCurrentUser().value?.street ?: "")
            number.value =
                number.setValueIfNullOrBlank(it?.number ?: getCurrentUser().value?.number ?: "")
            zipCode.value =
                zipCode.setValueIfNullOrBlank(it?.zipCode ?: getCurrentUser().value?.zipCode ?: "")
            city.value = city.setValueIfNullOrBlank(it?.city ?: getCurrentUser().value?.city ?: "")
            phoneNumber.value = phoneNumber.setValueIfNullOrBlank(
                it?.phoneNumber ?: getCurrentUser().value?.phoneNumber ?: ""
            )
        }
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

    private fun MutableLiveData<String>.errorCheck(errorField: MutableLiveData<Int>): Boolean {
        return if (this.value.isNullOrBlank()) {
            errorField.value = R.string.error_message_user_detail_field_missing
            false
        } else {
            errorField.value = 0
            true
        }
    }

    private fun MutableLiveData<String>.setValueIfNullOrBlank(valueToSet: String): String {
        return if (this.value.isNullOrBlank()) valueToSet
        else this.value!!
    }

    internal fun setRequestToConfirm(request: HelpRequestCreateDto) {
        requestToConfirm = request
        state.onNext(State.PROCESSING)
    }
}
