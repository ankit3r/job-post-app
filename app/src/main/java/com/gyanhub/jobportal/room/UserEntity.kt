package com.gyanhub.jobportal.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("UserTable")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val emailId: String,
    val fName: String,
    val Pass: String
)
