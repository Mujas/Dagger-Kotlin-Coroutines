package com.sap.kotlindaggermitch.di.subcomponents.auth

import androidx.lifecycle.ViewModel
import com.sap.kotlindaggermitch.di.ViewModelKey
import com.sap.kotlindaggermitch.viewmodel.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModule(mainViewModel : MainViewModel): ViewModel

}