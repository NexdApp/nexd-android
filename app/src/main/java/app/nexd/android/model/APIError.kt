package app.nexd.android.model

data class APIError(
    val errors: List<Error>,
    val statusCode: Int
)