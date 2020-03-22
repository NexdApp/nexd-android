package de.andrestefanov.android.nearbuy.api.network

import de.andrestefanov.android.nearbuy.api.data.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NearBuyService {

    @GET("auth/login")
    fun login(@Body body: LoginRequestBody): Single<LoginResponse>

    @GET("auth/register")
    fun register(@Body body: RegistrationBody) : Completable

    @GET("articles")
    fun getArticles() : Single<List<Article>>

    @POST("request")
    fun sendHelpRequest(@Body request: NewHelpRequest) : Completable
}