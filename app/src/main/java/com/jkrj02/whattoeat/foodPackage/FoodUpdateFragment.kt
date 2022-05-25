package com.jkrj02.whattoeat.foodPackage

import android.app.AlertDialog
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
import com.jkrj02.whattoeat.data.model.EatData
import com.jkrj02.whattoeat.data.model.FoodData
import com.jkrj02.whattoeat.databinding.FragmentFoodUpdateBinding
import com.jkrj02.whattoeat.viewModel.FoodViewModel

class FoodUpdateFragment : Fragment() {
    private var binding : FragmentFoodUpdateBinding?=null
    private val args by navArgs<FoodUpdateFragmentArgs>()
    private val foodViewModel : FoodViewModel by viewModels()

    private val TAG = "FoodUpdateFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodUpdateBinding.inflate(inflater, container, false)
//        Log.d(TAG, "onCreateView: ${args.foodUpdateArgs.name}")
        binding!!.txtNameFood.setText(args.foodUpdateArgs.name)
        binding!!.btnCancelUpdateFood.setOnClickListener {
            findNavController().popBackStack(R.id.foodListFragment,false)
        }
        binding!!.btnDeleteFood.setOnClickListener {
            AlertDialog.Builder(context)
                .setPositiveButton("Yes"){
                        _,_ ->
                    foodViewModel.delete(args.foodUpdateArgs)
                    Toast.makeText(context,"删除成功", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack(R.id.foodListFragment,false)
                }
                .setNegativeButton("No"){_,_ -> }
                .setTitle("删除")
                .setMessage("确认删除${args.foodUpdateArgs.name}吗？")
                .create().show()
        }
        binding!!.btnConfirmUpdateFood.setOnClickListener {
            val newName = binding!!.txtNameFood.text.toString()
            if(!newName.isEmpty() && newName.length < 10)
            {
                val newData = FoodData(args.foodUpdateArgs.id,newName,args.foodUpdateArgs.packer)
                foodViewModel.update(newData)
                Toast.makeText(context,"修改成功", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack(R.id.foodListFragment,false)
            }
            else if(newName.isEmpty())
            {
                Toast.makeText(context,"无内容", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(context,"长度过长", Toast.LENGTH_SHORT).show()
            }
        }
        return binding!!.root
    }

}