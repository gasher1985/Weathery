package com.example.weather.ui.weather.current

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.repository.ForecastRepository

class CurrentWeatherViewModelFactory(
    private val app: Application,
    private val forecastRepository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(app, forecastRepository) as T
    }
}