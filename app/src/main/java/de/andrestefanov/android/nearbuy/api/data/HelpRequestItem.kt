package de.andrestefanov.android.nearbuy.api.data

data class HelpRequestItem(
    val name: String,
    val amount: Int,
    var collected: Boolean = false
)