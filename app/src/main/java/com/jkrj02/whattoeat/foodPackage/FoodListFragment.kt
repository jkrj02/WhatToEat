package com.jkrj02.whattoeat.foodPackage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.jkrj02.whattoeat.databinding.FragmentFoodListBinding
import com.jkrj02.whattoeat.viewModel.FoodViewModel

class FoodListFragment : Fragment() {
    private var binding : FragmentFoodListBinding?=null
    private val adapter = FoodListAdapter()
    private val foodViewModel : FoodViewModel by viewModels()
    private val args by navArgs<FoodListFragmentArgs>()
    private var listener : HideNavigation?=null

    private val TAG = "FoodListFragment"

    interface HideNavigation
    {
        fun hide()
        fun undo()
        fun setNumberOfFood(num:Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        listener?.hide()
        binding = FragmentFoodListBinding.inflate(inflater,container,false)
        binding!!.recyclerViewFood.adapter = adapter
        binding!!.recyclerViewFood.layoutManager = GridLayoutManager(context,1)
        foodViewModel.getAllByPacker(args.foodListArgs).observe(viewLifecycleOwner)
        {
            adapter.updateData(it)
        }
        binding!!.btnAddFood.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodAddFragment(args.foodListArgs)
            findNavController().navigate(action)
        }
//        Log.d(TAG, "onCreateView: ${args.foodListArgs}")
        return binding!!.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is HideNavigation)
        {
            listener = context
        }
    }

}