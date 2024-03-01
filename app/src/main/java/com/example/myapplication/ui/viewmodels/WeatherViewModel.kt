package com.example.myapplication.ui.viewmodels

// WeatherViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    // LiveData or StateFlow to expose weather data to UI

    fun loadWeather(cityName: String) {
        viewModelScope.launch {
            val weatherData = repository.getWeatherByCity(cityName)
            // Update UI state here
        }
    }
}
