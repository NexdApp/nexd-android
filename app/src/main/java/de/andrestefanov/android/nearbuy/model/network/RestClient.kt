package de.andrestefanov.android.nearbuy.model.network

import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.model.data.HelpRequestItem
import java.text.SimpleDateFormat
import java.util.*

class RestClient {

    fun getNearbyOpenRequests() = listOf(
        HelpRequest(
            "0fb3a030-3b24-4f09-a856-853312baabc2",
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
            "e164bdd9-c5b8-44b0-b022-607a5d698854",
            "Bauer",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch"),
                HelpRequestItem("Eier")
            )
        )
    )

    fun getRequest(id: String): HelpRequest? {
        for (it in getNearbyOpenRequests().listIterator()) {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

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