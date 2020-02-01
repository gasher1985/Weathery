package com.example.weather.data.network

import com.example.weather.data.network.responce.WeatherResponce
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val API_KEY = "ae27d1fee35ec2fca44b8fcc5d8d6f4b"

//https://api.darksky.net/forecast/ae27d1fee35ec2fca44b8fcc5d8d6f4b/37.8267,-122.4233

interface DarkSkyApiService {


    @GET("/forecast/ae27d1fee35ec2fca44b8fcc5d8d6f4b/{latlong}")
    suspend fun getCurrentWeather(@Path("latlong") latlong: String): WeatherResponce

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): DarkSkyApiService {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)


            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.darksky.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DarkSkyApiService::class.java)
        }
    }
}