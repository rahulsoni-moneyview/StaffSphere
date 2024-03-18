package com.example.staffsphere.data.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.staffsphere.data.mappers.toHomeUiModel
import com.example.staffsphere.data.mappers.toProfileUiModel
import com.example.staffsphere.data.responses.HomeDataResponse
import com.example.staffsphere.data.responses.ProfileDataResponse
import com.example.staffsphere.domain.StandardResponse
import com.example.staffsphere.domain.models.HomeUiModel
import com.example.staffsphere.domain.models.ProfileUiModel
import com.example.staffsphere.domain.repository.IDataRepository
import com.example.staffsphere.utils.User
import com.example.staffsphere.utils.readJsonFromAssets
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepository @Inject constructor(private val context: Context) : IDataRepository {
    private val gson = Gson()
    override suspend fun fetchPeopleList(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchProfile(employeeId: Int): Flow<StandardResponse<ProfileUiModel>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                val jsonString = readJsonFromAssets("profile.json", context)
                val homeDataResponse = gson.fromJson(jsonString, ProfileDataResponse::class.java)
                    ?: throw IllegalStateException("Invalid JSON")
                homeDataResponse
            }
            if (response.success) {
                emit(StandardResponse.Success(response.toProfileUiModel()))
            } else {
                emit(StandardResponse.Failed("Something went wrong!"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchTeams() {
        TODO("Not yet implemented")
    }

    override suspend fun fetchHome(employeeId: Int): Flow<StandardResponse<HomeUiModel>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                val jsonString = readJsonFromAssets("home.json", context)
                val homeDataResponse = gson.fromJson(jsonString, HomeDataResponse::class.java)
                    ?: throw IllegalStateException("Invalid JSON")
                homeDataResponse
            }
            if (response.success) {
                emit(StandardResponse.Success(response.toHomeUiModel()))
            } else {
                emit(StandardResponse.Failed("Something went wrong!"))
            }
        }.flowOn(Dispatchers.IO)
    }


}