package com.example.staffsphere.presentation.screens.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.staffsphere.domain.StandardResponse
import com.example.staffsphere.domain.models.HomeUiModel
import com.example.staffsphere.domain.models.ProfileUiModel
import com.example.staffsphere.domain.repository.IDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: IDataRepository
) : ViewModel() {
    private val _liveProfileData = MutableStateFlow<StandardResponse<ProfileUiModel>?>(null)
    val liveProfileData = _liveProfileData.asStateFlow()

    fun fetchProfile() = viewModelScope.launch {
        repository.fetchProfile(12).collectLatest {
            _liveProfileData.value = it
        }
    }

}