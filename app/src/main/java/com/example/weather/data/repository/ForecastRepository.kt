package com.example.weather.data.repository

import androidx.lifecycle.LiveData
import com.example.weather.data.db.entity.Currently
import com.example.weather.data.db.entity.Data
import com.example.weather.data.network.responce.WeatherResponce

interface ForecastRepository {
    suspend fun getCurrentWeather(): LiveData<Currently>
    fun getDailyWeatherList(): LiveData<MutableList<Data>>
    fun getDailyWeatherItem(day: Int): LiveData<Data>
}