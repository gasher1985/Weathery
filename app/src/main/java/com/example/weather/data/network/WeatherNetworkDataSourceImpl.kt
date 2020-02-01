package com.example.weather.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.data.network.responce.Daily
import com.example.weather.data.network.responce.WeatherResponce
import com.example.weather.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl( private val darkSkyApiService: DarkSkyApiService) : WeatherNetworkDataSource {
    private val _downloadedCurrentWeather = MutableLiveData<WeatherResponce>()

    override val downloadedCurrentWeather: LiveData<WeatherResponce>
        get() = _downloadedCurrentWeather


    override suspend fun fetchWeather(location: String) {
        try {
            val fetchedCurrentWeather = darkSkyApiService
                .getCurrentWeather(location)
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "no internet connection", e)
        }
    }


}