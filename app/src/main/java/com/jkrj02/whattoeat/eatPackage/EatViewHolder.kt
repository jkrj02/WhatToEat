package com.jkrj02.whattoeat.eatPackage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jkrj02.whattoeat.data.model.EatData
import com.jkrj02.whattoeat.databinding.ListViewBinding

class EatViewHolder(private val binding: ListViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun binder(eatData: EatData)
    {
        binding.textView.text = eatData.name
    }

    companion object
    {
        fun from(parent: ViewGroup): EatViewHolder
        {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListViewBinding.inflate(layoutInflater, parent, false)
            return EatViewHolder(binding)
        }
    }
}