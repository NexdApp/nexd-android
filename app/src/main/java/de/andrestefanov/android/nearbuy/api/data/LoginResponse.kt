package de.andrestefanov.android.nearbuy.api.data

data class LoginResponse(
    val expiresIn: String,
    val accessToken: String,
    val id: Long
)