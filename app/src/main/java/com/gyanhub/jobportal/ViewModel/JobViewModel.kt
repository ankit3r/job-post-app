package com.gyanhub.jobportal.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gyanhub.jobportal.repository.JobRepository
import com.gyanhub.jobportal.room.JobTableEntity
import com.gyanhub.jobportal.room.UserEntity

class JobViewModel(private val repository: JobRepository) : ViewModel() {

    var byJobs: String = "All"

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

    private val byJobMutibleList = MutableLiveData<List<String>>()
    val byJob: LiveData<List<String>>
        get() = byJobMutibleList

    init {
        byJobMutibleList.postValue(listOf("All", "Job", "Internship"))
    }
}