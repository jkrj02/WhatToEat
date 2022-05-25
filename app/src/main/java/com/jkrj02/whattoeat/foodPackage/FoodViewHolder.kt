package com.jkrj02.whattoeat.foodPackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jkrj02.whattoeat.data.model.FoodData
import com.jkrj02.whattoeat.databinding.FoodListViewBinding

class FoodViewHolder(private val binding: FoodListViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun binder(foodData: FoodData)
    {
        binding.foodListTextView.text = foodData.name
    }

    companion object
    {
        fun from(parent: ViewGroup):FoodViewHolder
        {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FoodListViewBinding.inflate(layoutInflater, parent, false)
            return FoodViewHolder(binding)
        }
    }
}