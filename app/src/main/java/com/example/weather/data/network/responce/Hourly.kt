package com.example.weather.data.network.responce


import com.example.weather.data.db.entity.DataX

data class Hourly(
    val `data`: List<DataX>,
    val icon: String,
    val summary: String
)