package com.gyanhub.jobportal.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gyanhub.jobportal.repository.JobRepository
import com.gyanhub.jobportal.room.JobTableEntity
import com.gyanhub.jobportal.room.UserEntity

class JobViewModel(private val repository: JobRepository) : ViewModel() {

    fun getJob(): LiveData<List<JobTableEntity>> {
        return repository.getJobs()
    }

    fun addJobs(JobEntity: JobTableEntity) {
        repository.addJobs(JobEntity)
    }

    fun getUser(id: String, pass: String): LiveData<List<UserEntity>> {
        return repository.getUser(id, pass)
    }

    fun addUser(userEntity: UserEntity) {
        repository.addUser(userEntity)
    }

}