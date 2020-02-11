package com.example.weather.internal

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.weather.R
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt


@BindingAdapter("loadimg")
fun setImageUrl(imageView: ImageView, photo: String?){

    //Have to do this to prevent app crash. Need to fix some day(binding initially null for some reason)
    if (photo == null)
        Glide.with(imageView.context).load(R.drawable.ic_alien).into(imageView)
    when (photo) {
        "clear-day" -> Glide.with(imageView.context).load(R.drawable.ic_clear_day).into(imageView)
        "clear-night" -> Glide.with(imageView.context).load(R.drawable.ic_clear_night).into(imageView)
        "rain" -> Glide.with(imageView.context).load(R.drawable.ic_rain).into(imageView)
        "snow" -> Glide.with(imageView.context).load(R.drawable.ic_snow).into(imageView)
        "sleet" -> Glide.with(imageView.context).load(R.drawable.ic_sleet).into(imageView)
        "wind" -> Glide.with(imageView.context).load(R.drawable.ic_wind).into(imageView)
        "fog" -> Glide.with(imageView.context).load(R.drawable.ic_fog).into(imageView)
        "cloudy" -> Glide.with(imageView.context).load(R.drawable.ic_cloudy).into(imageView)
        "partly-cloudy-day" -> Glide.with(imageView.context).load(R.drawable.ic_partly_cloudy_day).into(imageView)
        "partly-cloudy-night" -> Glide.with(imageView.context).load(R.drawable.ic_partly_cloudy_night).into(imageView)
        else -> Glide.with(imageView.context).load(R.drawable.ic_alien).into(imageView)
    }
}

@BindingAdapter("dateString")
fun timeToString(textView: TextView, unixTime: Int) {
    val unixConversion = Instant.ofEpochSecond(unixTime.toLong())
    val localDate = unixConversion.atZone(ZoneId.systemDefault()).toLocalDate()
    textView.text = localDate.format(DateTimeFormatter.ofPattern("MMM dd"))
}

@BindingAdapter("hourString")
fun timeToHour(textView: TextView, unixTime: Int) {
    val unixConversion = Instant.ofEpochSecond(unixTime.toLong())
    val localDate = unixConversion.atZone(ZoneId.systemDefault()).toLocalDate()
    textView.text = localDate.format(DateTimeFormatter.ofPattern("hh a"))
}

@BindingAdapter("roundToInt")
fun roundToInt(textView: TextView, dTemp: Double) {
    textView.text = dTemp.roundToInt().toString()
}

@BindingAdapter("toPercent")
fun toPercent(textView: TextView, dPercent: Double ) {
    val format = NumberFormat.getPercentInstance(Locale.ENGLISH)
    textView.text = format.format(dPercent)
}

@BindingAdapter("windRoundToInt")
fun windRoundToInt(textView: TextView, dTemp: Double){
    val wind: String = dTemp.roundToInt().toString()
    textView.text = wind + "mph"
}

@BindingAdapter("visRoundToInt")
fun visRoundToInt(textView: TextView, dTemp: Double) {
    textView.text = dTemp.roundToInt().toString()
}

@BindingAdapter("gustRoundToInt")
fun gustRoundToInt(textView: TextView, dTemp: Double){
    val gust: String = dTemp.roundToInt().toString()
    textView.text = "Wind Gust: " + gust + "mph"
}

@BindingAdapter("feelsRoundToInt")
fun feelsRoundToInt(textView: TextView, dTemp: Double){
    val feels: String = dTemp.roundToInt().toString()
    textView.text = "Feels Like " + feels + "°F"
}

