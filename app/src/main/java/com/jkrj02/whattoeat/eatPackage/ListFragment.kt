package com.jkrj02.whattoeat.eatPackage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jkrj02.whattoeat.R
import com.jkrj02.whattoeat.databinding.FragmentListBinding
import com.jkrj02.whattoeat.viewModel.EatViewModel


class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null
    private val adapter = EatListAdapter()
    private val eatViewModel:EatViewModel by viewModels()
    private var listener: HideNavigation?=null

    interface HideNavigation
    {
        fun hide()
        fun undo()
        fun setNumberOfEat(num : Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listener?.undo()
        binding = FragmentListBinding.inflate(inflater,container,false)
        binding!!.recyclerView.adapter=adapter
        binding!!.recyclerView.layoutManager = GridLayoutManager(context,2)
        eatViewModel.allData.observe(viewLifecycleOwner)
        {
            adapter.updateData(it)
            listener?.setNumberOfEat(adapter.itemCount)
        }

        binding!!.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
//            var bindingMain= ActivityMainBinding.inflate(layoutInflater)
//            bindingMain.bottomNavigation.visibility=View.INVISIBLE
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