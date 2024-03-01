package com.example.myapplication.data.repository

import com.example.myapplication.data.network.WeatherService

class WeatherRepository(private val weatherService: WeatherService) {
    suspend fun getWeatherByCity(cityName: String) = weatherService.getWeatherByCity(cityName)
    // Add more methods for data handling
}