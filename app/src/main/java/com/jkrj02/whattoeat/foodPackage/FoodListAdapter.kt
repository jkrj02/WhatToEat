package com.jkrj02.whattoeat.foodPackage

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jkrj02.whattoeat.*
import com.jkrj02.whattoeat.data.model.FoodData

//Adapter类负责查询数据，实例化ViewHolder
class FoodListAdapter : RecyclerView.Adapter<FoodViewHolder>() {
    private var foodList= emptyList<FoodData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val oneData=foodList[position]

        holder.itemView.findViewById<ConstraintLayout>(R.id.foodListView).setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodUpdateFragment(oneData)
            holder.itemView.findNavController().navigate(action)
            true
        }

        holder.binder(oneData)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateData(newData:List<FoodData>)
    {
        val foodDiffUtil = FoodDiffUtil(foodList,newData)
        val diffResult = DiffUtil.calculateDiff(foodDiffUtil)
        this.foodList=newData
        //通知刷新
        diffResult.dispatchUpdatesTo(this)
    }

}