package com.example.weather.data.network.responce


import com.example.weather.data.db.entity.*

data class WeatherResponce(
    val currently: Currently,
    val daily: Daily,
    val flags: Flags,
    val hourly: Hourly,
    val latitude: Double,
    val longitude: Double,
    val minutely: Minutely,
    val offset: Int,
    val timezone: String
)