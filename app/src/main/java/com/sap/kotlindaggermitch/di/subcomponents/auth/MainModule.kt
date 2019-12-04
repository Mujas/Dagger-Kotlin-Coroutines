package com.sap.kotlindaggermitch.di.subcomponents.auth

import com.sap.kotlindaggermitch.storage.remote.ApiRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    /*@Provides
    fun provideAuthApi(retrofit: Retrofit): ApiRepo {
        return retrofit.create(ApiRepo::class.java)
    }*/
}