package com.example.weather.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "daily_weather")
data class Data(
    val apparentTemperatureHigh: Double,
    val apparentTemperatureHighTime: Int,
    val apparentTemperatureLow: Double,
    val apparentTemperatureLowTime: Int,
    val apparentTemperatureMax: Double,
    val apparentTemperatureMaxTime: Int,
    val apparentTemperatureMin: Double,
    val apparentTemperatureMinTime: Int,
    val cloudCover: Double,
    val dewPoint: Double,
    val humidity: Double,
    val icon: String,
    val moonPhase: Double,
    val ozone: Double,
    val precipIntensity: Double,
    val precipIntensityMax: Double,
    val precipIntensityMaxTime: Int,
    val precipProbability: Double,
    val precipType: String?,
    val pressure: Double,
    val summary: String,
    val sunriseTime: Int,
    val sunsetTime: Int,
    val temperatureHigh: Double,
    val temperatureHighTime: Int,
    val temperatureLow: Double,
    val temperatureLowTime: Int,
    val temperatureMax: Double,
    val temperatureMaxTime: Int,
    val temperatureMin: Double,
    val temperatureMinTime: Int,
    @PrimaryKey(autoGenerate = false)
    val time: Int,
    val uvIndex: Int,
    val uvIndexTime: Int,
    val visibility: Double,
    val windBearing: Int,
    val windGust: Double,
    val windGustTime: Int,
    val windSpeed: Double
)