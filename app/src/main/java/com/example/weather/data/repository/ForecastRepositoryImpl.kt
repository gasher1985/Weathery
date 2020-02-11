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
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : ForecastRepository {

    private lateinit var Lat: String
    private lateinit var Long: String

    init {

        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            Lat = it.latitude.toString()
            Long = it.longitude.toString()
        }
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

    private suspend fun initWeatherData() {
        if (isFetchedWeatherNeeded(ZonedDateTime.now().minusHours(1)))
            fetchWeather()
    }

    private suspend fun  fetchWeather() {

        weatherNetworkDataSource.fetchWeather("$Lat,$Long")
    }

    private fun isFetchedWeatherNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}