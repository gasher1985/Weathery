package com.example.weather.data.repository

import androidx.lifecycle.LiveData
import com.example.weather.data.db.CurrentlyDao
import com.example.weather.data.db.DailyDao
import com.example.weather.data.db.entity.Currently
import com.example.weather.data.db.entity.Data
import com.example.weather.data.network.WeatherNetworkDataSource
import com.example.weather.data.network.responce.WeatherResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentlyDao: CurrentlyDao,
    private val dailyDao: DailyDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFetchedWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(): LiveData<Currently> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext currentlyDao.getCurrently()
        }
    }

    override fun getDailyWeatherItem(day: Int): LiveData<Data> {
        return dailyDao.getDaily(day)
    }

    override fun getDailyWeatherList(): LiveData<MutableList<Data>> {
        return dailyDao.getDailyList()
    }

    private fun persistFetchedWeather(fetchedWeather: WeatherResponce){
        GlobalScope.launch(Dispatchers.IO) {

            currentlyDao.upsert(fetchedWeather.currently)
            dailyDao.startFresh() //Delete all cause not guaranteed to be same from last last pull
            dailyDao.insert(fetchedWeather.daily.day)
        }
    }

    private suspend fun initWeatherData() {
        if (isFetchedWeatherNeeded(ZonedDateTime.now().minusHours(1)))
            fetchWeather()
    }

    private suspend fun  fetchWeather() {
        weatherNetworkDataSource.fetchWeather("44.9631,-93.2666")
    }

    private fun isFetchedWeatherNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}