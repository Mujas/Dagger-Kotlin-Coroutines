package com.sap.kotlindaggermitch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.sap.kotlindaggermitch.models.User
import com.sap.kotlindaggermitch.network.ApiRepo
import com.sap.kotlindaggermitch.repository.main.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

class MainViewModel : ViewModel {

    private var _user: MutableLiveData<User> = MutableLiveData()

    var apiRepo: ApiRepo

    @Inject
    constructor(apiRepo: ApiRepo) {
        this.apiRepo = apiRepo
    }

    fun getUser(id: Int): LiveData<User> {
        //_user.value = UserRepository.getUsers(1).value;

        apiRepo.getUser(id)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { user ->
                    _user.value = user
                },
                { throwable ->
                    Log.e("Error", "${throwable}")
                })
        return _user
    }

    var job: CompletableJob? = null

    fun getUsers(userID: Int): LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        try {
                            val user = apiRepo.getUsers(userID)
                            withContext(Main) {
                                value = user
                                theJob.complete()
                            }
                        } catch (e: Exception) {
                            Log.e("Error", "$e")
                        }

                    }
                }
            }
        }
    }

    fun cancelJob() {
        job?.cancel()
    }
}