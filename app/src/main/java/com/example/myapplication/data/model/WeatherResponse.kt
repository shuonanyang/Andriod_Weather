package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName
//处理http返回来的数据，转会为WeatehrResponse对象

data class WeatherResponse(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main
    // 根据需要定义其他字段
)

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int
)
