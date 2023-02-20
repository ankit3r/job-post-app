package com.gyanhub.jobportal.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM JobTable")
    fun getJob(): LiveData<List<JobTableEntity>>

    @Insert
    fun addJob(jobTableEntity: JobTableEntity)

    @Insert
    fun addUser(userEntity: UserEntity)

    @Query("SELECT * FROM UserTable WHERE emailId =:email AND Pass =:pass")
    fun getUser(email: String, pass: String): LiveData<List<UserEntity>>


}