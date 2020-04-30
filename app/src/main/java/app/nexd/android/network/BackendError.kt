package app.nexd.android.network

import app.nexd.android.api.model.BackendErrorResponse

class BackendError(private val response: BackendErrorResponse) : Throwable() {

    val code = response.statusCode

    val errors = response.errors

    val errorCodes = errors.map { it.errorCode }

}