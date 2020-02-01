package com.example.weather.ui.weather.future.list

import androidx.lifecycle.ViewModel
import com.example.weather.data.repository.ForecastRepository

class FutureListWeatherViewModel(private val repo: ForecastRepository) : ViewModel() {

    val daily = repo.getDailyWeatherList()
}
