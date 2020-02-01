package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weather.data.db.entity.Data
import com.example.weather.databinding.FutureItemBinding
import com.example.weather.ui.weather.future.list.FutureListHolder

class FutureListAdapter(private val inflater: LayoutInflater): ListAdapter<Data, FutureListHolder>(
    DiffCallback
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FutureListHolder(
            FutureItemBinding.inflate(inflater, parent, false)
        )

    override fun onBindViewHolder(holder: FutureListHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

private object DiffCallback : DiffUtil.ItemCallback<Data>(){
    override fun areItemsTheSame(oldItem: Data, newItem: Data) =
        oldItem.time == newItem.time

    override fun areContentsTheSame(oldItem: Data, newItem: Data) =
        oldItem.apparentTemperatureLow == newItem.apparentTemperatureLow &&
                oldItem.apparentTemperatureHigh == newItem.apparentTemperatureHigh

}
