package com.sap.kotlindaggermitch.repository.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sap.kotlindaggermitch.models.User
import com.sap.kotlindaggermitch.network.ApiRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

object UserRepository {

    @set:Inject
    lateinit var apiRepo: ApiRepo

    private var _user: MutableLiveData<User> = MutableLiveData()

    var job: CompletableJob? = null

    fun getUsers(userID: Int): MutableLiveData<User> {
        apiRepo.getUser(userID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<User>() {
                override fun onError(e: Throwable) {
                }

                override fun onSuccess(t: User) {
                    _user.value = t
                }
            })

        return _user
    }

    fun cancelJob() {
        job?.cancel()
    }
}