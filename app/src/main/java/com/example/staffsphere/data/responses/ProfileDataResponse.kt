package com.example.staffsphere.data.responses

import com.example.staffsphere.domain.StandardResponse

data class ProfileDataResponse(
    val success: Boolean,
    val name: String,
    val imgUrl: String,
    val designation: String,
    val joinedMsg: String,
    val dept: String,
    val location: String,
    val email: String,
    val interests: List<String>,
    val projects: List<String>,
    val dob: String,
    val doj: String
)
