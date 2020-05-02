package app.nexd.android.ui.auth.register.model

import app.nexd.android.R

data class ViewState(
    val errorState: ErrorState,
    val loading: Loading
) {
    companion object {
        fun init(): ViewState {
            return ViewState(ErrorState.NoError, Loading.NotLoading)
        }
    }
}

sealed class Loading {
    object IsLoading : Loading()
    object NotLoading : Loading()
}

sealed class ErrorState {
    data class FormValidationErrors(val validationErrors: List<ValidationError>) : ErrorState()
    object NoError : ErrorState()
}

sealed class ValidationError(val message: Int) {
    object PhoneNumber : ValidationError(R.string.error_message_input_validation_phone_number_invalid)
    object Unknown : ValidationError(R.string.error_message_user_detail_field_missing)
    object Password : ValidationError(R.string.error_message_user_detail_field_missing)
    object Email : ValidationError(R.string.error_message_user_detail_field_missing)
    object Locality : ValidationError(R.string.error_message_user_detail_field_missing)
    object Zip : ValidationError(R.string.error_message_user_detail_field_missing)
    object HouseNumber : ValidationError(R.string.error_message_user_detail_field_missing)
    object Street : ValidationError(R.string.error_message_user_detail_field_missing)
    object AccountExist : ValidationError(R.string.error_message_input_validation_phone_number_invalid)
}