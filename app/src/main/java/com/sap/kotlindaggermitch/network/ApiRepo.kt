package com.sap.kotlindaggermitch.network

import com.sap.kotlindaggermitch.models.User
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRepo {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Single<User>

    @GET("users/{id}")
    suspend fun getUsers(@Path("id") id: Int): User
}