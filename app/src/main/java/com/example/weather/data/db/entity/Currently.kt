package com.example.weather.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currently_weather")
data class Currently(
    val apparentTemperature: Double,
    val cloudCover: Double,
    val dewPoint: Double,
    val humidity: Double,
    val icon: String,
    val nearestStormBearing: Int,
    val nearestStormDistance: Int,
    val ozone: Double,
    val precipIntensity: Double,
    val precipProbability: Double,
    val pressure: Double,
    val summary: String,
    val temperature: Double,
    val time: Int,
    val uvIndex: Int,
    val visibility: Double,
    val windBearing: Int,
    val windGust: Double,
    val windSpeed: Double
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}