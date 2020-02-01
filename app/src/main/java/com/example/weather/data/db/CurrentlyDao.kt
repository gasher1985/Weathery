package com.example.weather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.db.entity.Currently


@Dao
interface CurrentlyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(weatherEntry: Currently)

    @Query("select * from currently_weather where id = 0")
    fun getCurrently(): LiveData<Currently>
}
