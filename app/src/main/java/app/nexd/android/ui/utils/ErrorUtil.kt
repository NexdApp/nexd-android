package app.nexd.android.ui.utils

import app.nexd.android.model.APIError
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import retrofit2.Response


class ErrorUtil {
    companion object {

        fun parseError(response: Response<*>): APIError {
            try {
                val errorBody = response.errorBody()?.string() ?: return APIError(
                    -1,
                    arrayOf("Unable to parse error")
                    , "parse error"
                )
                return Gson().fromJson(errorBody, APIError::class.java)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()

                return APIError(
                    -1,
                    arrayOf("Unable to parse error")
                    , "parse error"
                )
            }
        }

        fun parseError(throwable: Throwable): APIError {
            if (throwable is HttpException) {
                throwable.response()?.let {
                    return parseError(it)
                } ?: run {
                    return APIError(
                        -1,
                        arrayOf("Unable to parse error")
                        , "parse error"
                    )
                }
            } else {
                return APIError(
                    -1,
                    arrayOf("Unable to parse error")
                    , "parse error"
                )
            }
        }
    }
}