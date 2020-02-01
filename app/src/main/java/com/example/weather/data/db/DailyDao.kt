package com.example.weather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.db.entity.Data


@Dao
interface DailyDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dailyWeather: List<Data>)

    @Query("delete from daily_weather")
    suspend fun startFresh()

    @Query("select * from daily_weather")
    fun getDailyList(): LiveData<MutableList<Data>>

    @Query("select * from daily_weather where time = :time ")
    fun getDaily(time: Int): LiveData<Data>
}