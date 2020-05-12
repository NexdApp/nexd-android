package app.nexd.android.network

import app.nexd.android.api.model.BackendErrorResponse
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response

class BackendErrorInterceptor : Interceptor {

    private val gson = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

            if (!response.isSuccessful) {
                val code = response.code

                if (code in 400..499) {
                    val body = response.body

                    body?.let {
                        val backendErrorResponse =
                            gson.fromJson(body.charStream(), BackendErrorResponse::class.java)
                        throw BackendError(backendErrorResponse)
                    }
                }
            }

        return response
    }

}