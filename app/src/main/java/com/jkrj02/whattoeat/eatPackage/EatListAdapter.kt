package com.jkrj02.whattoeat.eatPackage

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jkrj02.whattoeat.R
import com.jkrj02.whattoeat.data.model.EatData

//Adapter类负责查询数据，实例化ViewHolder
class EatListAdapter : RecyclerView.Adapter<EatViewHolder>() {
    private var eatList= emptyList<EatData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EatViewHolder {
        return EatViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: EatViewHolder, position: Int) {
        val oneData=eatList[position]

        holder.itemView.findViewById<ConstraintLayout>(R.id.listView).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToFoodListFragment(oneData.id)
            holder.itemView.findNavController().navigate(action)
        }
        holder.itemView.findViewById<ConstraintLayout>(R.id.listView).setOnLongClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(oneData)
            holder.itemView.findNavController().navigate(action)
            true
        }

        holder.binder(oneData)
    }

    override fun getItemCount(): Int {
        return eatList.size
    }

    fun updateData(newData:List<EatData>)
    {
        val eatDiffUtil = EatDiffUtil(eatList,newData)
        val diffResult = DiffUtil.calculateDiff(eatDiffUtil)
        this.eatList=newData
        //通知刷新
        diffResult.dispatchUpdatesTo(this)
    }

}