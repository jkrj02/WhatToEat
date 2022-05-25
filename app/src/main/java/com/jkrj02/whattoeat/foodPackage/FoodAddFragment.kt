package com.jkrj02.whattoeat.foodPackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jkrj02.whattoeat.R
import com.jkrj02.whattoeat.data.model.FoodData
import com.jkrj02.whattoeat.databinding.FragmentFoodAddBinding
import com.jkrj02.whattoeat.viewModel.FoodViewModel

class FoodAddFragment : Fragment() {
    private var binding : FragmentFoodAddBinding?=null
    private val foodViewModel : FoodViewModel by viewModels()
    private val args by navArgs<FoodAddFragmentArgs>()

    private val TAG = "FoodAddFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodAddBinding.inflate(inflater,container,false)
        binding!!.buttonFoodCancel.setOnClickListener {
            findNavController().popBackStack(R.id.foodListFragment,false)
        }
        binding!!.btnFoodConfirm.setOnClickListener {
            val name = binding!!.textFoodInput.text.toString()
            if(!name.isEmpty() && name.length < 10)
            {
                val newData = FoodData(0,name,args.foodAddArgs)
                foodViewModel.insert(newData)
                Log.d(TAG, "onCreateView: ${newData.packer}")
                Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show()
                findNavController().popBackStack(R.id.foodListFragment,false)
            }
            else if(name.isEmpty())
            {
                Toast.makeText(context,"无内容",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(context,"长度过长",Toast.LENGTH_SHORT).show()
            }
        }
        return binding!!.root
    }

}