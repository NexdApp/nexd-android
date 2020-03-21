package de.andrestefanov.android.nearbuy.model.data

data class HelpRequest(
    val id: String,
    val name: String,
    val location: String,
    val items: List<String>
)