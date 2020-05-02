package app.nexd.android.ui.auth.register.model

sealed class Event {
    data class UserRegistrationEvent(
        val phone: String,
        val houseNumber: String,
        val zip: String,
        val street: String,
        val locality: String
    ): Event()
}