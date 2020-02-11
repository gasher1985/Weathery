package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.db.entity.DataX
import com.example.weather.ui.weather.current.HourlyListHolder

class HourlyAdapter(private val list: MutableList<DataX>)
    : RecyclerView.Adapter<HourlyListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyListHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HourlyListHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: HourlyListHolder, position: Int) {
        val hourly: DataX = list[position]
        holder.bind(hourly)
    }

    override fun getItemCount(): Int = list.size

}