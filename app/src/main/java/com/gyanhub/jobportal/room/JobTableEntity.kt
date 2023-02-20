package com.gyanhub.jobportal.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("JobTable")
data class JobTableEntity (
    @PrimaryKey(autoGenerate = true)
    val jobId: Int,
    val jobType : String,
    val jobCyName : String,
    val jobDiscription : String,
    val jobPay : String,
    val jobSkillRequrired : String,
    val jobPostOpportunitity : String,
    val whoCanApply : String,
    val whNo : String,
)