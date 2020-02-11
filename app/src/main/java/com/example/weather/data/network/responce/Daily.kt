package com.example.weather.data.network.responce

import com.example.weather.data.db.entity.Daily
import com.google.gson.annotations.SerializedName


data class Daily(
    @SerializedName("data")
    val day: List<Daily>
)