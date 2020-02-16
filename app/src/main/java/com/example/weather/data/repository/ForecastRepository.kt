package com.example.weather.data.repository

import androidx.lifecycle.LiveData
import com.example.weather.data.db.entity.Currently
import com.example.weather.data.db.entity.Daily
import com.example.weather.data.db.entity.DataX

interface ForecastRepository {
    suspend fun getCurrentWeather(lat: String, long: String): LiveData<Currently>
    fun getDailyWeatherList(): LiveData<MutableList<Daily>>
    fun getDailyWeatherItem(day: Int): LiveData<Daily>
    fun getHourlyWeatherList(): LiveData<MutableList<DataX>>
}