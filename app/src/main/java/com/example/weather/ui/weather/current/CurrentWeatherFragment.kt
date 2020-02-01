package com.example.weather.ui.weather.current

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.weather.R
import com.example.weather.data.network.ConnectivityInterceptorImpl
import com.example.weather.data.network.DarkSkyApiService
import com.example.weather.data.network.WeatherNetworkDataSourceImpl
import com.example.weather.internal.BearingToDirection
import com.example.weather.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)
        bindUI()
    }
    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE
            setVisibility(it.visibility)
            setTemp(it.temperature, it.apparentTemperature)
            setWindAndDirection(it.windSpeed, it.windBearing, it.windGust)
            setWeatherIcon(it.icon)
            setSummary(it.summary)

        })
    }

    private fun setTemp(actualTemp: Double, feelsLike: Double){
        textView_temperature.text = "$actualTemp°F"
        textView_feels_like_temperature.text = "Feels like: $feelsLike°F"
    }

    private fun setVisibility(visibility: Double){
        textView_visibility.text = "Visibility: $visibility mi"
    }

    private fun setWindAndDirection(windSpeed: Double, windBearing: Int, windGust: Double){
        val windDirection = BearingToDirection(windBearing).Direction()

        textView_wind.text = "Wind: $windDirection, $windSpeed mph "
        textView_windGust.text = "Gust: $windGust mph"
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

    private fun setSummary(summary: String){
        textView_summary.text = summary
    }

}
