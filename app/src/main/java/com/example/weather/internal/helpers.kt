package com.example.weather.internal

import android.widget.ImageView
import com.example.weather.R
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.text.NumberFormat
import java.util.*

class Helpers {
    fun setWeatherIcon(view: ImageView, icon: String) {
        when (icon) {
            "clear-day" -> view.setImageResource(R.drawable.ic_clear_day)
            "clear-night" -> view.setImageResource(R.drawable.ic_clear_night)
            "rain" -> view.setImageResource(R.drawable.ic_rain)
            "snow" -> view.setImageResource(R.drawable.ic_snow)
            "sleet" -> view.setImageResource(R.drawable.ic_sleet)
            "wind" -> view.setImageResource(R.drawable.ic_wind)
            "fog" -> view.setImageResource(R.drawable.ic_fog)
            "cloudy" -> view.setImageResource(R.drawable.ic_cloudy)
            "partly-cloudy-day" -> view.setImageResource(R.drawable.ic_partly_cloudy_day)
            "partly-cloudy-night" -> view.setImageResource(R.drawable.ic_partly_cloudy_night)
            else -> view.setImageResource(R.drawable.ic_alien)
        }
    }

    fun timeToMonthDay(unixTime: Int): String {
        val unixConversion = Instant.ofEpochSecond(unixTime.toLong())
        val localDate = unixConversion.atZone(ZoneId.systemDefault()).toLocalDate()
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd"))
    }

    fun timeToHour( unixTime: Int): String {

        val unixConversion = Instant.ofEpochSecond(unixTime.toLong())
        val localDate = unixConversion.atZone(ZoneId.systemDefault()).toLocalDateTime()
        return localDate.format(DateTimeFormatter.ofPattern("hh a MMM dd")).toString()
        //val unixConversion = Instant.ofEpochSecond(unixTime.toLong())
        //val localDate = unixConversion.atZone(ZoneId.systemDefault()).toLocalDate()
        //format.format(DateTimeFormatter.ofPattern("hh a"))
    }

    fun toPercent(dPercent: Double ): String {
        val format = NumberFormat.getPercentInstance(Locale.ENGLISH)
        return format.format(dPercent)
    }

}