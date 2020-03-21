package de.andrestefanov.android.nearbuy.model.network

import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.model.data.HelpRequestItem
import java.text.SimpleDateFormat
import java.util.*

class RestClient {

    fun getNearbyOpenRequests() = listOf(
        HelpRequest(
            UUID.randomUUID().toString(),
            "Müller",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch"),
                HelpRequestItem("Eier"),
                HelpRequestItem("Klopapier")
            )
        ),
        HelpRequest(
            UUID.randomUUID().toString(),
            "Bauer",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch"),
                HelpRequestItem("Eier")
            )
        )
    )

    fun getMyRequests() = listOf(
        HelpRequest(
            UUID.randomUUID().toString(),
            "Müller",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch"),
                HelpRequestItem("Eier"),
                HelpRequestItem("Klopapier")
            )
        ),
        HelpRequest(
            UUID.randomUUID().toString(),
            "Müller",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Seife"),
                HelpRequestItem("Butter")
            )
        )
    )

}