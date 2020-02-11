package com.example.weather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.db.entity.DataX

@Dao
interface HourlyDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hourlyWeather: List<DataX>)

    @Query("delete from hourly_table")
    suspend fun startFresh()

    @Query("select * from hourly_table")
    fun getDailyList(): LiveData<MutableList<DataX>>

}