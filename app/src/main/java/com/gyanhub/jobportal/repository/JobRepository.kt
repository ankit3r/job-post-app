package com.gyanhub.jobportal.repository

import androidx.lifecycle.LiveData
import com.gyanhub.jobportal.room.Dao
import com.gyanhub.jobportal.room.JobTableEntity
import com.gyanhub.jobportal.room.UserEntity

class JobRepository(private val dao: Dao) {

    fun getJobs(): LiveData<List<JobTableEntity>> {
        return dao.getJob()
    }

    fun addJobs(jobEntity: JobTableEntity) {
        dao.addJob(jobEntity)
    }

    fun getUser(email: String, pass: String): LiveData<List<UserEntity>> {
        return dao.getUser(email, pass)
    }

    fun addUser(userEntity: UserEntity) {
        dao.addUser(userEntity)
    }




}