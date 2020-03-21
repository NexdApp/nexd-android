package de.andrestefanov.android.nearbuy.model.network

import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.model.data.HelpRequestItem
import java.text.SimpleDateFormat
import java.util.*

class RestClient {

    companion object {
        const val MAX_ACCEPTING_REQUESTS = 20
        private var acceptedRequests = mutableListOf<HelpRequest>()
    }

    fun getNearbyOpenRequests() = listOf(
        HelpRequest(
            "0fb3a030-3b24-4f09-a856-853312baabc2",
            "Müller",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch", 2),
                HelpRequestItem("Eier", 1),
                HelpRequestItem("Klopapier", 5)
            )
        ),
        HelpRequest(
            "e164bdd9-c5b8-44b0-b022-607a5d698854",
            "Bauer",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch", 1),
                HelpRequestItem("Eier", 3)
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
                HelpRequestItem("Milch", 3),
                HelpRequestItem("Eier", 2),
                HelpRequestItem("Klopapier", 1)
            )
        ),
        HelpRequest(
            UUID.randomUUID().toString(),
            "Müller",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Seife", 4),
                HelpRequestItem("Butter", 2)
            )
        )
    )

    fun getAcceptedRequests(): List<HelpRequest> {
        return acceptedRequests
    }

    fun containsAcceptedRequest(id: String): Boolean {
        getAcceptedRequests().forEach {
            if (it.id == id) {
                return true
            }
        }
        return false
    }

    fun acceptRequest(request: HelpRequest): Boolean {
        if (containsAcceptedRequest(request.id)) {
            return false
        }

        acceptedRequests.add(request)
        return true
    }

}