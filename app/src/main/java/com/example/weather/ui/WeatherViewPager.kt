package com.example.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.weather.R
import com.example.weather.adapters.NEXT_WEEK_PAGE_INDEX
import com.example.weather.adapters.ViewPagerAdapter
import com.example.weather.adapters.WEATHER_PAGE_INDEX
import com.example.weather.databinding.FragmentWeatherViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator



class WeatherViewPager : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWeatherViewPagerBinding.inflate(inflater, container, false)
        val tablayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tablayout, viewPager) {tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            WEATHER_PAGE_INDEX -> getString(R.string.tabOne)
            NEXT_WEEK_PAGE_INDEX -> getString(R.string.tabTwo)
            else -> null
        }
    }
}
