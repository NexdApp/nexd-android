package app.nexd.android.model

import com.google.gson.annotations.SerializedName

class APIError(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: Array<String>,
    @SerializedName("error")
    val error: String
) {
    val firstMessage: String
    get() = message.firstOrNull() ?: error
}