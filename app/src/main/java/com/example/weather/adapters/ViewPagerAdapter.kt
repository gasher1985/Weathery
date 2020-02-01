package com.example.weather.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weather.ui.weather.current.CurrentWeatherFragment
import com.example.weather.ui.weather.future.list.FutureListWeatherFragment

const val WEATHER_PAGE_INDEX = 0
const val NEXT_WEEK_PAGE_INDEX = 1

class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        WEATHER_PAGE_INDEX to { CurrentWeatherFragment() },
        NEXT_WEEK_PAGE_INDEX to { FutureListWeatherFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}