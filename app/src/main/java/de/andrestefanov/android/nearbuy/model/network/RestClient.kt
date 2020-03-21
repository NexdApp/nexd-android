package de.andrestefanov.android.nearbuy.model.network

import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import java.util.*

class RestClient {

    fun getNearbyOpenRequests() = listOf(
        HelpRequest(
            UUID.randomUUID().toString(),
            "Müller",
            "Marienplatz 1",
            listOf(
                "Milch",
                "Eier",
                "Klopapier"
            )
        ),
        HelpRequest(
            UUID.randomUUID().toString(),
            "Bauer",
            "Marienplatz 1",
            listOf(
                "Milch",
                "Eier"
            )
        )
    )

    fun getMyRequests() = listOf(
        HelpRequest(
            UUID.randomUUID().toString(),
            "Müller",
            "Marienplatz 1",
            listOf(
                "Milch",
                "Eier",
                "Klopapier"
            )
        )
    )

}