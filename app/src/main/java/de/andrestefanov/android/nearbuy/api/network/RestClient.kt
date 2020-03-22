package de.andrestefanov.android.nearbuy.api.network

import de.andrestefanov.android.nearbuy.api.data.*
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RestClient {

    companion object {
        const val MAX_ACCEPTING_REQUESTS = 20
        private var acceptedRequests: MutableList<HelpRequest> = mutableListOf()
    }

    private val service: NearBuyService


    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://wirvsvirus-nearbuy.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(NearBuyService::class.java)
    }

    fun login(login: String, password: String): Single<LoginResponse> {
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

    fun getArticles(): Single<List<Article>> {
        return service.getArticles()
            .subscribeOn(Schedulers.io())
    }

    fun sendHelpRequest(
        request: NewHelpRequest
    ): Completable {
        return service.sendHelpRequest(
            request
        ).subscribeOn(Schedulers.io())
    }


    // DUMMY APIS

    fun getNearbyOpenRequests() = listOf(
        HelpRequest(
            "6022cdb3-b791-4496-8f3b-e35e50482bdb",
            "Müller",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch", 1),
                HelpRequestItem("Eier", 2),
                HelpRequestItem("Klopapier", 4)
            )
        ),
        HelpRequest(
            "94b2eb76-7348-423b-84df-c2b9468f8c15",
            "Bauer",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch", 3),
                HelpRequestItem("Eier", 1)
            )
        )
    )

    fun getMyRequests() = listOf(
        HelpRequest(
            "9b69adcd-453a-4cb2-8d4d-51d97c6e2f9e",
            "Müller",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Milch", 4),
                HelpRequestItem("Eier", 5),
                HelpRequestItem("Klopapier", 2)
            )
        ),
        HelpRequest(
            "0eaa08d9-dd93-4165-9df5-d8c8a4307a43",
            "Müller",
            Calendar.getInstance().time,
            "Marienplatz 1",
            listOf(
                HelpRequestItem("Seife", 1),
                HelpRequestItem("Butter", 2)
            )
        )
    )

    /**
     * search accepted and open request for request with given id
     */
    fun getRequest(id: String): HelpRequest? {
        for (it in getNearbyOpenRequests().listIterator()) {
            if (it.id == id) {
                return it
            }
        }
        for (it in getAcceptedRequests().listIterator()) {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

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