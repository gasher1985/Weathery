package com.example.weather.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.weather.data.repository.ForecastRepository
import com.example.weather.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()
    }
    val daily by lazyDeferred{
        forecastRepository.getHourlyWeatherList()
    }

}
