package com.sap.kotlindaggermitch.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sap.kotlindaggermitch.models.User
import com.sap.kotlindaggermitch.storage.local.user.UsersDao
import com.sap.kotlindaggermitch.storage.local.user.UsersEntity
import com.sap.kotlindaggermitch.storage.remote.ApiRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiRepo: ApiRepo,
    private val userDao: UsersDao
) {

    private var _user: MutableLiveData<User> = MutableLiveData()

    var job: CompletableJob? = null
    fun getUsers(userID: Int): LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(Dispatchers.IO + theJob).launch {
                        try {
                            val user = apiRepo.getUsers(userID)
                            saveToLocal(user)
                            withContext(Dispatchers.Main) {
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

    private fun saveToLocal(user: User) {
        //var insert = userDao.insert(UsersEntity(1, "Mujasam", "mujasam@gmail.com", "google.com"))
        userDao.insert(UsersEntity.to(user))
        Log.e("DB USer*","${userDao.getUsers()}")
    }

    fun getUser(id: Int): LiveData<User> {

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

    fun cancelJob() {
        job?.cancel()
    }
}