package com.example.weather.ui.weather.current

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.db.entity.DataX
import com.example.weather.internal.Helpers
import kotlin.math.roundToInt

class HourlyListHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.hourly_item, parent, false)) {

    private var tempView: TextView = itemView.findViewById(R.id.temp)
    private var precipView: TextView = itemView.findViewById(R.id.precipPercent)
    private var windView: TextView = itemView.findViewById(R.id.windSpeed)
    private var timeView: TextView = itemView.findViewById(R.id.textView_time)
    private var iconView: ImageView = itemView.findViewById(R.id.imageView)
    private val helpers = Helpers()

    fun bind(hourly: DataX){
        tempView.text = hourly.temperature.roundToInt().toString()
        precipView.text = helpers.toPercent(hourly.precipProbability)
        windView.text = hourly.windSpeed.roundToInt().toString()
        timeView.text = helpers.timeToHour(hourly.time)
        helpers.setWeatherIcon(iconView, hourly.icon)

    }

}