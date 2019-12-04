package com.sap.kotlindaggermitch.storage.remote

import com.sap.kotlindaggermitch.models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRepo {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Single<User>

    @GET("users/{id}")
    suspend fun getUsers(@Path("id") id: Int): User
}