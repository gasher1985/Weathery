package com.example.weather.ui.weather.future.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.db.entity.Data
import com.example.weather.data.network.responce.Daily
import com.example.weather.data.repository.ForecastRepository
import com.example.weather.internal.lazyDeferred
import kotlinx.coroutines.*


class FutureDetailWeatherViewModel( repo: ForecastRepository, date: Int) : ViewModel() {

    val weather = repo.getDailyWeatherItem(date)

}
