package de.andrestefanov.android.nearbuy.api.network

import de.andrestefanov.android.nearbuy.api.data.*
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RestClient {

    private val service: NearBuyService

    init {
        val client = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wirvsvirus-nearbuy.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(NearBuyService::class.java)
    }

    fun login(login: String, password: String): Completable {
        return service.login(LoginRequestBody(login, password))
            .subscribeOn(Schedulers.io())
    }

    fun register(
        email: String,
        firstName: String,
        lastName: String,
        password: String
    ): Completable {
        return service.register(RegistrationBody(email, firstName, lastName, password))
            .subscribeOn(Schedulers.io())
    }

    fun getArticles() : Single<List<Article>> {
        return service.getArticles()
            .subscribeOn(Schedulers.io())
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