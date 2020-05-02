package app.nexd.android.ui.auth.register

import app.nexd.android.R
import app.nexd.android.api.model.BackendErrorEntry
import app.nexd.android.network.BackendError
import app.nexd.android.ui.auth.register.model.Effect
import app.nexd.android.ui.auth.register.model.ErrorState
import app.nexd.android.ui.auth.register.model.ValidationError
import app.nexd.android.ui.utils.Either

class ErrorTranslator {

    fun translate(error: Throwable): Either<Effect.ShowErrorMessage, ErrorState> {
        return when (error) {
            is BackendError -> translateBackendError(error)
            else -> Either.Left(Effect.ShowErrorMessage(R.string.unknow_error))
        }
    }

    private fun translateBackendError(error: BackendError): Either<Effect.ShowErrorMessage, ErrorState> {
        val validationErrors = error.errorCodes.map { errorCode ->
            when (errorCode) {
                BackendErrorEntry.ErrorCodeEnum.VALIDATION_PHONENUMBER_INVALID -> {
                    ValidationError.PhoneNumber
                }
                else -> ValidationError.Unknown
            }
        }
        return Either.Right(ErrorState.FormValidationErrors(validationErrors))
    }
}