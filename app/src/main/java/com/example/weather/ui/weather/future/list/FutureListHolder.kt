package com.example.weather.ui.weather.future.list

import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.example.weather.data.db.entity.Data
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

    fun bind(futureData: Data){
        binding.model = futureData
        binding.holder = this
        binding.executePendingBindings()
    }
}