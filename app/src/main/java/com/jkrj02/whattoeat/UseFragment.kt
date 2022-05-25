package com.jkrj02.whattoeat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jkrj02.whattoeat.databinding.FragmentUseBinding

class UseFragment : Fragment() {
    private var binding : FragmentUseBinding?=null
    private var listener: HideNavigation?=null

    interface HideNavigation
    {
        fun hide()
        fun undo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listener?.hide()
        binding = FragmentUseBinding.inflate(inflater,container,false)
        binding!!.btnKnow.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment,false)
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