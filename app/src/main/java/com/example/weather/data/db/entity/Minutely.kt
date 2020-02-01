package com.example.weather.data.db.entity


import com.example.weather.data.db.entity.DataXX

data class Minutely(
    val `data`: List<DataXX>,
    val icon: String,
    val summary: String
)