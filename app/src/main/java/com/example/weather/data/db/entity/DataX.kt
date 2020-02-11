package com.example.weather.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "hourly_table")
data class DataX(
    val apparentTemperature: Double,
    val cloudCover: Double,
    val dewPoint: Double,
    val humidity: Double,
    val icon: String,
    val ozone: Double,
    val precipIntensity: Double,
    val precipProbability: Double,
    val precipType: String?,
    val pressure: Double,
    val summary: String,
    val temperature: Double,
    @PrimaryKey(autoGenerate = false)
    val time: Int,
    val uvIndex: Int,
    val visibility: Double,
    val windBearing: Int,
    val windGust: Double,
    val windSpeed: Double
)