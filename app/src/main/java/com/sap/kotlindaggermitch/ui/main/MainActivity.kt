package com.sap.kotlindaggermitch.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sap.kotlindaggermitch.R
import com.sap.kotlindaggermitch.viewmodel.main.MainViewModel
import com.sap.kotlindaggermitch.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var mainVideModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVideModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java)

        mainVideModel.getUsers(1).observe(this, Observer { user ->
            Log.e("User","User is ${user}")
        })
    }
}
