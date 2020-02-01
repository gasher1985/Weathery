package com.example.weather.data.network.responce

import androidx.room.Entity
import com.example.weather.data.db.entity.Data
import com.google.gson.annotations.SerializedName

@Entity(tableName = "week_weather")
data class Daily(
    @SerializedName("data")
    val day: List<Data>
)