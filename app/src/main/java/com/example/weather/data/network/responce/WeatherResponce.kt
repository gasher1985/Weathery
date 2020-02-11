package com.example.weather.data.network.responce


import com.example.weather.data.db.entity.*

data class WeatherResponce(
    val currently: Currently,
    val daily: Daily,
    val hourly: Hourly
)