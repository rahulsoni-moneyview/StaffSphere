package com.example.staffsphere.domain.repository

import com.example.staffsphere.domain.StandardResponse
import com.example.staffsphere.domain.models.HomeUiModel
import com.example.staffsphere.domain.models.ProfileUiModel
import com.example.staffsphere.utils.User
import kotlinx.coroutines.flow.Flow

interface IDataRepository {
    suspend fun fetchPeopleList(): List<User>
    suspend fun fetchProfile(employeeId: Int): Flow<StandardResponse<ProfileUiModel>>
    suspend fun fetchTeams()
    suspend fun fetchHome(employeeId: Int): Flow<StandardResponse<HomeUiModel>>
}