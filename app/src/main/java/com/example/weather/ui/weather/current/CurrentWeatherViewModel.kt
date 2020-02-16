package com.example.weather.ui.weather.current

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.db.entity.Currently
import com.example.weather.data.repository.ForecastRepository
import com.example.weather.internal.gps.LocationLiveData
import com.example.weather.internal.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.LateInitKodein

class CurrentWeatherViewModel(
    application: Application,
    private val forecastRepository: ForecastRepository
) : AndroidViewModel(application) {

    private val locationData = LocationLiveData(application)

    fun getLocationData() = locationData

    suspend fun getWeather(Lat: String, Lon: String): LiveData<Currently>{
        return withContext(Dispatchers.IO) {
            forecastRepository.getCurrentWeather(Lat, Lon)
        }
    }

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather("44.977753", "-93.2650108")
    }
    val daily by lazyDeferred{
        forecastRepository.getHourlyWeatherList()
    }

}
