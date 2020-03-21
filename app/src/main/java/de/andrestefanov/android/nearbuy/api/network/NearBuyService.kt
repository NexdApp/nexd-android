package de.andrestefanov.android.nearbuy.api.network

import de.andrestefanov.android.nearbuy.api.data.Article
import de.andrestefanov.android.nearbuy.api.data.LoginRequestBody
import de.andrestefanov.android.nearbuy.api.data.RegistrationBody
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET

interface NearBuyService {

    @GET("auth/login")
    fun login(@Body body: LoginRequestBody): Completable

    @GET("auth/register")
    fun register(@Body body: RegistrationBody) : Completable

    @GET("articles")
    fun getArticles() : Single<List<Article>>

}