package com.example.myapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.datastore.UserPreferencesRepository
import kotlinx.coroutines.launch
//使用这个 UserPreferencesRepository 来读取或更新用户的首选项。
class SettingsViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    // 使用 asLiveData() 将 Flow 转换为 LiveData
    val temperatureUnit = userPreferencesRepository.temperatureUnit.asLiveData()

    fun updateTemperatureUnit(useCelsius: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.updateTemperatureUnit(useCelsius)
        }
    }
}