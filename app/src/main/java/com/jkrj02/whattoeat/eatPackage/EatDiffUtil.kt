package com.jkrj02.whattoeat.eatPackage

import androidx.recyclerview.widget.DiffUtil
import com.jkrj02.whattoeat.data.model.EatData

class EatDiffUtil(private val oldList: List<EatData>,private val newList: List<EatData>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem=oldList[oldItemPosition]
        val newItem=newList[newItemPosition]
        return oldItem.id==newItem.id && oldItem.name==newItem.name && oldItem.FoodNumber==newItem.FoodNumber
    }

}