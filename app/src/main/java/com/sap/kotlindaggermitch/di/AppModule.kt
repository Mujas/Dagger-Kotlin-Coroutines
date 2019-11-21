package com.sap.kotlindaggermitch.di

import android.content.Context
import androidx.annotation.NonNull
import com.sap.kotlindaggermitch.network.ApiRepo
import com.sap.kotlindaggermitch.utils.AppConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Global module here you can define all application level components
 * Example Retrofit instance, Glide instance, etc...
 */
@Module
class AppModule {

    @Provides
    fun providesContext(application: BaseApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}