package app.nexd.android.models

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("statusCode")
    var code: String,
    @SerializedName("message")
    var message: Array<String>,
    @SerializedName("error")
    var error: String
)