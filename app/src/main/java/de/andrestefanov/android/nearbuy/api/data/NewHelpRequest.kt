package de.andrestefanov.android.nearbuy.api.data

data class NewHelpRequest(
    var articles: List<Article>,
    var address: String,
    var zipCode: String,
    var city: String,
    var additionalRequest: String,
    var deliveryComment: String,
    var phoneNumber: String
)