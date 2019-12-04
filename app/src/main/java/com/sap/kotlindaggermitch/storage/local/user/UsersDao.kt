package com.sap.kotlindaggermitch.storage.local.user

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sap.kotlindaggermitch.models.User


@Dao
interface UsersDao {

    @Query("SELECT * from UsersEntity")
    fun getUsers(): LiveData<List<UsersEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UsersEntity)

    @Delete
    fun remove(user: UsersEntity)
}