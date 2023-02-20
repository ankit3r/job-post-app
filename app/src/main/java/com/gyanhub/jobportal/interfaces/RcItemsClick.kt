package com.gyanhub.jobportal.interfaces

import com.gyanhub.jobportal.room.JobTableEntity

interface RcItemsClick {
    fun itemsClick(position: Int,data : List<JobTableEntity>)
}