package com.sap.kotlindaggermitch.di

import androidx.lifecycle.ViewModelProvider
import com.sap.kotlindaggermitch.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelProviderFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}