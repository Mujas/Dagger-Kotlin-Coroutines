package com.sap.kotlindaggermitch.storage.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sap.kotlindaggermitch.storage.local.user.UsersDao
import com.sap.kotlindaggermitch.storage.local.user.UsersEntity

@Database(entities = [UsersEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UsersDao
}