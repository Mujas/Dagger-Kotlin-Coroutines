package com.sap.kotlindaggermitch.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sap.kotlindaggermitch.models.User
import com.sap.kotlindaggermitch.repository.UserRepository
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private var _user: MutableLiveData<User> = MutableLiveData()

    @Inject
    lateinit var userRepository: UserRepository

    fun getUsers(id: Int): LiveData<User> {
        return userRepository.getUsers(id)
    }
}