package com.example.weather.data.repository

import android.app.Application
import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.example.weather.data.db.CurrentlyDao
import com.example.weather.data.db.DailyDao
import com.example.weather.data.db.HourlyDao
import com.example.weather.data.db.entity.Currently
import com.example.weather.data.db.entity.Daily
import com.example.weather.data.db.entity.DataX
import com.example.weather.data.network.WeatherNetworkDataSource
import com.example.weather.data.network.responce.WeatherResponce
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentlyDao: CurrentlyDao,
    private val dailyDao: DailyDao,
    private val hourlyDao: HourlyDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFetchedWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(lat: String, long: String): LiveData<Currently> {
        return withContext(Dispatchers.IO) {
            initWeatherData(lat, long)
            return@withContext currentlyDao.getCurrently()
        }
    }

    override fun getDailyWeatherItem(day: Int): LiveData<Daily> {
        return dailyDao.getDaily(day)
    }

    override fun getDailyWeatherList(): LiveData<MutableList<Daily>> {
        return dailyDao.getDailyList()
    }

    override fun getHourlyWeatherList(): LiveData<MutableList<DataX>> {
        return hourlyDao.getDailyList()
    }

    private fun persistFetchedWeather(fetchedWeather: WeatherResponce){
        GlobalScope.launch(Dispatchers.IO) {

            currentlyDao.upsert(fetchedWeather.currently)
            dailyDao.startFresh() //Delete all cause not guaranteed to be same from last last pull
            dailyDao.insert(fetchedWeather.daily.day)
            hourlyDao.startFresh()
            hourlyDao.insert(fetchedWeather.hourly.data)
        }
    }

    private suspend fun initWeatherData(lat: String, long: String) {
        if (isFetchedWeatherNeeded(ZonedDateTime.now().minusHours(1)))
            fetchWeather(lat, long)
    }

    private suspend fun  fetchWeather(lat: String, long: String) {

        weatherNetworkDataSource.fetchWeather("$lat,$long")
    }

    private fun isFetchedWeatherNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}