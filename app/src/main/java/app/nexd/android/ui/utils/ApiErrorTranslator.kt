package app.nexd.android.ui.utils

import app.nexd.android.model.APIError
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import retrofit2.Response

class ApiErrorTranslator {

    fun translate(throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> translateHttpException(throwable.response())
            else -> "unknown error type"
        }
    }

    private fun translateHttpException(response: Response<*>?): String {
        return when (response) {
            null -> "response was null"
            else -> parseResponse(response)
        }
    }

    private fun parseResponse(response: Response<*>): String {
        val errorBody = response.errorBody()?.string() ?: return "error body was empty"
        return try {
            Gson().fromJson(errorBody, APIError::class.java)
                .errors
                .joinToString { it.errorDescription }
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            "could not parse error json with body: $errorBody"
        }
    }
}