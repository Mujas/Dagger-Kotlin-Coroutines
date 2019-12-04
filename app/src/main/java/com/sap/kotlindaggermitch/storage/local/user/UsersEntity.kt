package com.sap.kotlindaggermitch.storage.local.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sap.kotlindaggermitch.models.User
import java.util.*

@Entity
data class UsersEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "user_name")
    val userName: String?,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "website")
    val website: String?
) {
    companion object {
        fun to(user: User): UsersEntity {
            return UsersEntity(
                id = user.id,
                userName = user.username,
                email = user.email,
                website = user.website

            )
        }
    }
}