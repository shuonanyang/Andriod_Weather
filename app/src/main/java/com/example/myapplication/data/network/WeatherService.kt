package com.example.myapplication.data.network
// WeatherService.kt
import com.example.myapplication.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getWeatherByCity(@Query("q") cityName: String): WeatherResponse
    // Define more API methods here
}
