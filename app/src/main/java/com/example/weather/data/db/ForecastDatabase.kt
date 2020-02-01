package com.example.weather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.data.db.entity.Currently
import com.example.weather.data.db.entity.Data

@Database(entities = [Currently::class, Data::class], version = 1)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentlyDao(): CurrentlyDao
    abstract fun dailyDao(): DailyDao

    companion object{
        @Volatile private var instance: ForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "forecast.dp")
                .build()
    }
}