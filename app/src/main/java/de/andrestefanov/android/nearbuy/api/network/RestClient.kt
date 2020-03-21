package de.andrestefanov.android.nearbuy.api.network

import de.andrestefanov.android.nearbuy.api.data.HelpRequest
import de.andrestefanov.android.nearbuy.api.data.HelpRequestItem
import de.andrestefanov.android.nearbuy.api.data.LoginRequestBody
import de.andrestefanov.android.nearbuy.api.data.RegistrationBody
import io.reactivex.Completable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import java.util.*

class RestClient {

    private val service: NearBuyService

    init {
        val client = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wirvsvirus-nearbuy.herokuapp.com/api/")
            .client(client)
            .build()

        service = retrofit.create(NearBuyService::class.java)
    }

    fun login(login: String, password: String): Completable {
        return service.login(LoginRequestBody(login, password))
    }

    fun register(
        email: String,
        firstName: String,
        lastName: String,
        password: String
    ): Completable {
        return service.register(RegistrationBody(email, firstName, lastName, password))
    }


    // DUMMY APIS

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