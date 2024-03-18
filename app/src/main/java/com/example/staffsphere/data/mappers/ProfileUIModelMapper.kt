package com.example.staffsphere.data.mappers

import com.example.staffsphere.data.responses.ProfileDataResponse
import com.example.staffsphere.domain.models.ProfileUiModel

fun ProfileDataResponse.toProfileUiModel(): ProfileUiModel {
    return ProfileUiModel(
        name = this.name,
        imgUrl = this.imgUrl,
        designation = this.designation,
        joinedMsg = this.joinedMsg,
        dept = this.dept,
        location = this.location,
        email = this.email,
        interests = this.interests,
        dob = this.dob,
        doj = this.doj
    )
}


