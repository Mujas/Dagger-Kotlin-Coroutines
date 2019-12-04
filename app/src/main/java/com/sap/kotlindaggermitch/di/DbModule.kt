package com.sap.kotlindaggermitch.di

import android.app.Application
import androidx.room.Room
import com.sap.kotlindaggermitch.storage.local.AppDatabase
import com.sap.kotlindaggermitch.storage.local.user.UsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, "Users.db")
            .allowMainThreadQueries().build()
    }

    /*
     * We need the Dao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */
    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: AppDatabase): UsersDao {
        return appDatabase.userDao()
    }
}