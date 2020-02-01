package com.example.weather.ui.weather.future.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.data.db.entity.Data
import com.example.weather.data.repository.ForecastRepositoryImpl
import com.example.weather.internal.BearingToDirection
import kotlinx.android.synthetic.main.future_detail_weather_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import kotlin.math.roundToInt

class FutureDetailWeatherFragment() : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val repo: ForecastRepositoryImpl by instance()

    private lateinit var viewModel: FutureDetailWeatherViewModel

    private lateinit var data: Data
    //private val args: FutureDetailWeatherFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_detail_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val viewModelFactory = FutureDetailWeatherViewModelFactory(repo, args.date)
       // viewModel = ViewModelProviders.of(this, viewModelFactory).get(FutureDetailWeatherViewModel::class.java)
       /* viewModel.weather.observe(this, Observer {
            if (it == null) return@Observer
            Glide.with(this).load("https://images.unsplash.com/photo-1532751788314-cf521c13ad75?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2107&q=80")
                .centerCrop()
                .into(imageView_background!!)
            setWeatherIcon(it.icon)
            setTemp(it.temperatureHigh, it.temperatureLow)
            setSummary(it.summary)
            setWindAndDirection(it.windSpeed, it.windBearing, it.windGust)
            setVisibility(it.visibility)

        })*/
    }

    private fun setWeatherIcon(icon: String){
        when (icon) {
            "clear-day" -> imageView_condition_icon.setImageResource(R.drawable.ic_clear_day)
            "clear-night" -> imageView_condition_icon.setImageResource(R.drawable.ic_clear_night)
            "rain" -> imageView_condition_icon.setImageResource(R.drawable.ic_rain)
            "snow" -> imageView_condition_icon.setImageResource(R.drawable.ic_snow)
            "sleet" -> imageView_condition_icon.setImageResource(R.drawable.ic_sleet)
            "wind" -> imageView_condition_icon.setImageResource(R.drawable.ic_wind)
            "fog" -> imageView_condition_icon.setImageResource(R.drawable.ic_fog)
            "cloudy" -> imageView_condition_icon.setImageResource(R.drawable.ic_cloudy)
            "partly-cloudy-day" -> imageView_condition_icon.setImageResource(R.drawable.ic_partly_cloudy_day)
            "partly-cloudy-night" -> imageView_condition_icon.setImageResource(R.drawable.ic_partly_cloudy_night)
            else -> imageView_condition_icon.setImageResource(R.drawable.ic_alien)
        }
    }

    private fun setTemp(actualTemp: Double, feelsLike: Double){
        val aTemp = actualTemp.roundToInt()
        val fTemp = feelsLike.roundToInt()
        textView_temperature.text = "$aTemp°F"
        textView_feels_like_temperature.text = "Feels like: $fTemp°F"
    }

    private fun setWindAndDirection(windSpeed: Double, windBearing: Int, windGust: Double){
        val windDirection = BearingToDirection(windBearing).Direction()

        textView_wind_bearing.text = "Wind: $windDirection, $windSpeed mph "
        textView_windGust.text = "Gust: $windGust mph"
    }

    private fun setSummary(summary: String){
        textView_summary.text = summary
    }

    private fun setVisibility(visibility: Double){
        textView_visibility.text = "Visibility: $visibility mi"
    }

}
