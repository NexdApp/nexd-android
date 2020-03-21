package de.andrestefanov.android.nearbuy.api.network

import de.andrestefanov.android.nearbuy.api.data.*
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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

    fun getArticles() : Single<List<Article>> {
        return service.getArticles()
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