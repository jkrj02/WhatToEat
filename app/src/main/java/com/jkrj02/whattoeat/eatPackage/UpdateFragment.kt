package com.jkrj02.whattoeat.eatPackage

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jkrj02.whattoeat.R
import com.jkrj02.whattoeat.data.model.EatData
import com.jkrj02.whattoeat.databinding.FragmentUpdateBinding
import com.jkrj02.whattoeat.viewModel.EatViewModel
import com.jkrj02.whattoeat.viewModel.FoodViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateFragment : Fragment() {
    private var binding: FragmentUpdateBinding?=null
    private var listener: HideNavigation?=null
    private val args by navArgs<UpdateFragmentArgs>()
    private val eatViewModel:EatViewModel by viewModels()
    private val foodViewModel:FoodViewModel by viewModels()

    interface HideNavigation
    {
        fun hide()
        fun undo()
    }

    private val TAG = "UpdateFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        listener?.hide()
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        binding!!.txtName.setText(args.newItem.name)
        binding!!.btnCancelUpdate.setOnClickListener {
            findNavController().popBackStack(R.id.listFragment,false)
        }
        binding!!.btnConfirmUpdate.setOnClickListener {
            val newName = binding!!.txtName.text.toString()
            if(!newName.isEmpty() && newName.length < 10)
            {
                val newData = EatData(args.newItem.id,newName,args.newItem.FoodNumber)
                eatViewModel.update(newData)
                Toast.makeText(context,"修改成功", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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
        binding!!.btnDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setPositiveButton("Yes"){
                _,_ ->
                    foodViewModel.viewModelScope.launch(Dispatchers.IO)
                    {
                        val deleteList = foodViewModel.getAllByPackerForDelete(args.newItem.id)
                        for (item in deleteList)
                        {
                            foodViewModel.delete(item)
                        }
                    }
                    eatViewModel.delete(args.newItem)
                    Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }
                .setNegativeButton("No"){_,_ -> }
                .setTitle("删除")
                .setMessage("确认删除${args.newItem.name}吗？")
                .create().show()
        }
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