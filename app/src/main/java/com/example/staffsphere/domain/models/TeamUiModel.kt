package com.example.staffsphere.domain.models

import com.example.staffsphere.utils.TeamName
import com.example.staffsphere.utils.User

data class TeamUiModel(
    val team: TeamName? = null,
    val memberCount: Int? = null,
    val members: List<ProfileUiModel>? = null
)