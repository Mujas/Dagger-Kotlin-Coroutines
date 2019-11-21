package com.sap.kotlindaggermitch.di.subcomponents.auth

import com.sap.kotlindaggermitch.network.ApiRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Inject

@Module
class MainModule {

    @Provides
    fun provideAuthApi(retrofit: Retrofit): ApiRepo {
        return retrofit.create(ApiRepo::class.java)
    }
}