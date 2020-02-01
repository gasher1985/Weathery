package com.example.weather.data.network

import androidx.lifecycle.LiveData
import com.example.weather.data.network.responce.Daily
import com.example.weather.data.network.responce.WeatherResponce

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<WeatherResponce>

    suspend fun fetchWeather(location: String)
}