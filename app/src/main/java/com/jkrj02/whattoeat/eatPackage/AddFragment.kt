package com.jkrj02.whattoeat.eatPackage

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.jkrj02.whattoeat.R
import com.jkrj02.whattoeat.data.model.EatData
import com.jkrj02.whattoeat.databinding.FragmentAddBinding
import com.jkrj02.whattoeat.viewModel.EatViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFragment : Fragment() {

    private var binding : FragmentAddBinding? = null
    private var listener : HideNavigation?=null
    private val eatViewModel : EatViewModel by viewModels()


    interface HideNavigation
    {
        fun hide()
        fun undo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //回调listener的方法隐藏导航栏
        listener?.hide()
        binding = FragmentAddBinding.inflate(inflater,container,false)
        binding!!.btnCancel.setOnClickListener {
            findNavController().popBackStack(R.id.listFragment,false)
        }
        binding!!.btnConfirm.setOnClickListener {
            val eatName = binding!!.textInput.text.toString()
            if(!eatName.isEmpty() && eatName.length < 10)
            {
                val newData = EatData(0,eatName,0)
                eatViewModel.insert(newData)
                Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
            else if(eatName.isEmpty())
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is HideNavigation)
        {
            listener=context
        }
    }
}