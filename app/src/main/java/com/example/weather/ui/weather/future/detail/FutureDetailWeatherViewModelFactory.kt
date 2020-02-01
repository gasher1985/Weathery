package com.example.weather.ui.weather.future.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.repository.ForecastRepository

class FutureDetailWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository,
    private var date: Int
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureDetailWeatherViewModel(forecastRepository, date) as T
    }
}