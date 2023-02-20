package com.gyanhub.jobportal.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gyanhub.jobportal.repository.JobRepository

class JobFactory(private val repository: JobRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JobViewModel(repository) as T
    }
}