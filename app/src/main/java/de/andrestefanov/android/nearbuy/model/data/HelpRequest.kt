package de.andrestefanov.android.nearbuy.model.data

import java.util.*

data class HelpRequest(
    val id: String,
    val name: String,
    val createdDate: Date,
    val location: String,
    val items: List<HelpRequestItem>
)