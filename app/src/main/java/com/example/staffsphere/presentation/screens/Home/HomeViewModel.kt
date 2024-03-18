package com.example.staffsphere.presentation.screens.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.staffsphere.data.mappers.toHomeUiModel
import com.example.staffsphere.data.repository.DataRepository
import com.example.staffsphere.data.responses.HomeDataResponse
import com.example.staffsphere.domain.StandardResponse
import com.example.staffsphere.domain.models.HomeUiModel
import com.example.staffsphere.domain.repository.IDataRepository
import com.example.staffsphere.utils.readJsonFromAssets
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IDataRepository
) : ViewModel() {
    private val _liveHomeData = MutableStateFlow<StandardResponse<HomeUiModel>?>(null)
    val liveHomeData = _liveHomeData.asStateFlow()

    fun fetchHomeData() = viewModelScope.launch {
        repository.fetchHome(12).collectLatest {
            _liveHomeData.value = it
        }
    }
}
