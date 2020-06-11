package app.nexd.android.network

import app.nexd.android.api.model.BackendErrorEntry
import app.nexd.android.api.model.BackendErrorResponse

class BackendError(response: BackendErrorResponse) : Throwable() {

    val code: Long = response.statusCode

    val errors: List<BackendErrorEntry> = response.errors

    val errorCodes: List<BackendErrorEntry.ErrorCodeEnum> = errors.map { it.errorCode }


}