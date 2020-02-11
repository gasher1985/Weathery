package com.example.weather.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.weather.R
import com.example.weather.adapters.HourlyAdapter
import com.example.weather.internal.BearingToDirection
import com.example.weather.internal.Helpers
import com.example.weather.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import kotlin.math.roundToInt

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()
    lateinit var imageView: ImageView
    val helper = Helpers()
    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        imageView = imageView_condition_icon
        Glide.with(this).load("https://images.unsplash.com/photo-1532751788314-cf521c13ad75?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2107&q=80")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView_background!!)
        hourlyRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        bindUI()
    }
    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        val dailyWeather = viewModel.daily.await()
        currentWeather.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            group_loading.visibility = View.GONE
            setVisibility(it.visibility)
            setTemp(it.temperature, it.apparentTemperature)
            helper.setWeatherIcon(imageView, it.icon)
            setWindAndDirection(it.windSpeed, it.windBearing, it.windGust)
            setSummary(it.summary)
        })

        dailyWeather.observe(viewLifecycleOwner, Observer {
            if (it == null || it.isEmpty()) return@Observer


                it.removeAt(0)
                hourlyRecycler.adapter = HourlyAdapter(it)


        })
    }

    private fun setTemp(actualTemp: Double, feelsLike: Double){
        textView_temperature.text = "${actualTemp.roundToInt()}°F"
        textView_feels_like_temperature.text = "Feels like: ${feelsLike.roundToInt()}°F"
    }

    private fun setVisibility(visibility: Double){
        textView_visibility.text = "Visibility: ${visibility.roundToInt()} mi"
    }

    private fun setWindAndDirection(windSpeed: Double, windBearing: Int, windGust: Double){
        val windDirection = BearingToDirection(windBearing).Direction()

        textView_wind.text = "Wind: $windDirection, ${windSpeed.roundToInt()} mph "
        textView_windGust.text = "Gust: ${windGust.roundToInt()} mph"
    }



    private fun setSummary(summary: String){
        textView_summary.text = summary
    }

}
