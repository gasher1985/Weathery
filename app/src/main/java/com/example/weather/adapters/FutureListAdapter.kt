package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weather.data.db.entity.Daily
import com.example.weather.databinding.FutureItemBinding
import com.example.weather.ui.weather.future.list.FutureListHolder

class FutureListAdapter(private val inflater: LayoutInflater): ListAdapter<Daily, FutureListHolder>(
    DailyDiffCallback
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FutureListHolder(
            FutureItemBinding.inflate(inflater, parent, false)
        )

    override fun onBindViewHolder(holder: FutureListHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

private object DailyDiffCallback : DiffUtil.ItemCallback<Daily>(){
    override fun areItemsTheSame(oldItem: Daily, newItem: Daily) =
        oldItem.time == newItem.time

    override fun areContentsTheSame(oldItem: Daily, newItem: Daily) =
        oldItem.apparentTemperatureLow == newItem.apparentTemperatureLow &&
                oldItem.apparentTemperatureHigh == newItem.apparentTemperatureHigh

}
