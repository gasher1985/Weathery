package com.example.weather

import android.app.Application
import android.content.Context
import com.example.weather.data.db.ForecastDatabase
import com.example.weather.data.network.*
import com.example.weather.data.repository.ForecastRepository
import com.example.weather.data.repository.ForecastRepositoryImpl
import com.example.weather.ui.weather.current.CurrentWeatherViewModelFactory
import com.example.weather.ui.weather.future.list.FutureListWeatherViewModelFactory
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentlyDao() }
        bind() from singleton { instance<ForecastDatabase>().dailyDao() }
        bind() from singleton { instance<ForecastDatabase>().hourlyDao() }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { DarkSkyApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(),instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
        bind() from provider { FutureListWeatherViewModelFactory(instance())}
        bind() from provider { ForecastRepositoryImpl(instance(), instance(), instance(),instance())}

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}