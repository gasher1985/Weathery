package com.example.weather.ui.weather.future.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.db.entity.Daily
import com.example.weather.databinding.FutureItemBinding

class FutureListHolder(private val binding: FutureItemBinding): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setClickListener {
            if (binding.extendGroup.visibility == View.GONE){
                binding.extendGroup.visibility = View.VISIBLE
            } else {
                binding.extendGroup.visibility = View.GONE
            }

        }
    }

    fun bind(futureDaily: Daily){
        binding.model = futureDaily
        binding.holder = this
        binding.executePendingBindings()
    }
}