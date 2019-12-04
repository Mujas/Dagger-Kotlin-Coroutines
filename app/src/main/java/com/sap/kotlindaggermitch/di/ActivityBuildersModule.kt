package com.sap.kotlindaggermitch.di

import com.sap.kotlindaggermitch.di.subcomponents.auth.MainModule
import com.sap.kotlindaggermitch.di.subcomponents.auth.MainViewModelModule
import com.sap.kotlindaggermitch.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [MainViewModelModule::class,
            MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}