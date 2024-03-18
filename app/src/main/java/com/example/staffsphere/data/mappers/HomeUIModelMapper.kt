package com.example.staffsphere.data.mappers

import com.example.staffsphere.data.responses.HomeDataResponse
import com.example.staffsphere.domain.models.HomeUiModel

fun HomeDataResponse.toHomeUiModel(): HomeUiModel {
    return HomeUiModel(
        employeeName = this.employeeName,
        welcomeMsg = this.welcomeMsg
    )
}