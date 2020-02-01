package com.example.weather.data.db.entity


import com.google.gson.annotations.SerializedName

data class DataXX(
    val precipIntensity: Double,
    val precipIntensityError: Double,
    val precipProbability: Double,
    val precipType: String?,
    val time: Int
)