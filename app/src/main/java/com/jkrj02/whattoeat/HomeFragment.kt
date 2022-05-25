package com.jkrj02.whattoeat

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.jkrj02.whattoeat.data.EatDatabase
import com.jkrj02.whattoeat.data.model.EatData
import com.jkrj02.whattoeat.data.model.FoodData
import com.jkrj02.whattoeat.databinding.FragmentHomeBinding
import com.jkrj02.whattoeat.repository.EatRepository
import com.jkrj02.whattoeat.viewModel.EatViewModel
import com.jkrj02.whattoeat.viewModel.FoodViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration
import kotlin.random.Random

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val eatViewModel: EatViewModel by viewModels()
    private val foodViewModel : FoodViewModel by viewModels()
//    private val adapter = EatListAdapter()
    private var listener: HideNavigation?=null

    interface HideNavigation
    {
        fun hide()
        fun undo()
        fun getNumberOfEat() : Int
        fun getNumberOfFood() : Int
    }

    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listener?.undo()
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        var tmp : Int
        eatViewModel.viewModelScope.launch(Dispatchers.IO) {
            tmp = eatViewModel.getNumber()
            if(tmp == 0)
            {
                val newData = EatData(0,"示例(使用指南在右上角)",1)
                eatViewModel.insert(newData)
            }
        }
        //初始化各组件是否可见
        binding!!.btnStart.visibility = View.VISIBLE
        binding!!.answerText.visibility = View.VISIBLE
        binding!!.FoodText.visibility = View.INVISIBLE
        binding!!.FoodPackerText.visibility = View.INVISIBLE
        binding!!.btnStart.setOnClickListener {
            val choose = binding!!.spinner.selectedItem.toString()
            var num : Int
            var minId : Int
            var maxId : Int
            if(choose == "食堂")
            {
                eatViewModel.viewModelScope.launch(Dispatchers.IO)
                {
//                    binding!!.answerText.text = eatViewModel.getMinId().toString()
                    num = eatViewModel.getNumber()
                    minId = eatViewModel.getMinId()
                    maxId = eatViewModel.getMaxId()
                    if(num == 0)
                    {
                        Looper.prepare()
                        Toast.makeText(context,"无数据",Toast.LENGTH_SHORT).show()
//                        Log.d(TAG, "onCreateView: ${binding!!.btnStart.visibility}")
                        Looper.loop()
                    }
                    else if(num == 1)
                    {
                        setInvisible()
                        val item = eatViewModel.getById(minId)
                        setContent(item)
                        setVisible()
                    }
                    else
                    {
                        setInvisible()
                        //做一个动态效果
                        var cnt = 1
                        var theChosenNumber: Int
                        var theLastChosenNumber : Int
                        //先rand出来一个符合要求的，并记录为lastOne
                        theChosenNumber = Random.nextInt(minId,maxId+1)
                        while(eatViewModel.countById(theChosenNumber) == 0)
                        {
                            theChosenNumber = Random.nextInt(minId,maxId+1)
                        }
                        var item = eatViewModel.getById(theChosenNumber)
                        setContent(item)
                        theLastChosenNumber = theChosenNumber
                        while(cnt < 10)
                        {
                            theChosenNumber = Random.nextInt(minId,maxId+1)
                            //确保两次的不一样，获得动态效果
                            while(eatViewModel.countById(theChosenNumber) == 0 || theChosenNumber == theLastChosenNumber)
                            {
                                theChosenNumber = Random.nextInt(minId,maxId+1)
                            }
                            item = eatViewModel.getById(theChosenNumber)
                            setContent(item)
                            theLastChosenNumber = theChosenNumber
                            Thread.sleep(150)
                            cnt += 1
                        }
                        setVisible()
                    }
                    setVisible()
                }
            }
            else
            {
                foodViewModel.viewModelScope.launch(Dispatchers.IO)
                {
                    num = foodViewModel.getNumber()
                    Log.d(TAG, "onCreateView: num:$num")
                    if(num == 0)
                    {
                        Looper.prepare()
                        Toast.makeText(context,"无数据",Toast.LENGTH_SHORT).show()
                        Looper.loop()
                    }
                    else if(num == 1)
                    {
                        setInvisibleForFood()
                        minId = foodViewModel.getMinId()
                        maxId = foodViewModel.getMaxId()
                        val item = foodViewModel.getById(minId)
                        val itemPacker = eatViewModel.getById(item.packer)
//                        Log.d(TAG, "onCreateView: ${item.id}")
                        setFoodContent(item,itemPacker)
                        setVisibleForFood()
                    }
                    else
                    {
                        setInvisibleForFood()
                        minId = foodViewModel.getMinId()
                        maxId = foodViewModel.getMaxId()
                        Log.d(TAG, "onCreateView: $minId $maxId")
                        var cnt = 1
                        var theChosenNumber: Int
                        var theLastChosenNumber : Int
                        theChosenNumber = Random.nextInt(minId,maxId+1)
                        while(foodViewModel.countById(theChosenNumber) == 0)
                        {
                            theChosenNumber = Random.nextInt(minId,maxId+1)
                        }
                        var item = foodViewModel.getById(theChosenNumber)
                        var itemPacker = eatViewModel.getById(item.packer)
                        setFoodContent(item,itemPacker)
//                        Log.d(TAG, "onCreateView: $theChosenNumber")
                        theLastChosenNumber = theChosenNumber
                        while(cnt < 10)
                        {
//                            Log.d(TAG, "onCreateView: $cnt")
                            theChosenNumber = Random.nextInt(minId,maxId+1)
                            //确保两次的不一样，获得动态效果
                            while(foodViewModel.countById(theChosenNumber) == 0 || theChosenNumber == theLastChosenNumber)
                            {
                                theChosenNumber = Random.nextInt(minId,maxId+1)
//                                Log.d(TAG, "onCreateView: $theChosenNumber")
                            }
                            item = foodViewModel.getById(theChosenNumber)
//                            Log.d(TAG, "onCreateView: $minId $maxId $theChosenNumber")
                            itemPacker = eatViewModel.getById(item.packer)
                            setFoodContent(item,itemPacker)
//                            Log.d(TAG, "onCreateView: $theChosenNumber")
                            theLastChosenNumber = theChosenNumber
                            Thread.sleep(150)
                            cnt += 1
                        }
                        setVisibleForFood()
                    }
                    setVisibleForFood()
                }
            }
            binding!!.btnStart.visibility = View.VISIBLE
        }

        binding!!.imgAsk.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_useFragment)
        }
        return binding!!.root
    }

    private fun setContent(item : EatData)
    {
        eatViewModel.viewModelScope.launch(Dispatchers.Main)
        {
            binding!!.answerText.text=item.name
        }
    }
    private fun setFoodContent(item : FoodData, itemPacker : EatData)
    {
        foodViewModel.viewModelScope.launch(Dispatchers.Main)
        {
            binding!!.FoodText.text = item.name
            binding!!.FoodPackerText.text = itemPacker.name
        }
    }
    private fun setInvisible()
    {
        eatViewModel.viewModelScope.launch(Dispatchers.Main)
        {
            binding!!.btnStart.visibility = View.INVISIBLE
            binding!!.answerText.visibility = View.VISIBLE
            binding!!.FoodText.visibility = View.INVISIBLE
            binding!!.FoodPackerText.visibility = View.INVISIBLE
        }
    }
    private fun setVisible()
    {
        eatViewModel.viewModelScope.launch(Dispatchers.Main)
        {
            Log.d(TAG, "setVisible: doit")
            binding!!.btnStart.visibility = View.VISIBLE
        }
    }
    private fun setInvisibleForFood()
    {
        foodViewModel.viewModelScope.launch(Dispatchers.Main)
        {
            binding!!.btnStart.visibility = View.INVISIBLE
            binding!!.answerText.visibility = View.INVISIBLE
            binding!!.FoodText.visibility = View.VISIBLE
            binding!!.FoodPackerText.visibility = View.VISIBLE
        }
    }
    private fun setVisibleForFood()
    {
        foodViewModel.viewModelScope.launch(Dispatchers.Main)
        {
            binding!!.btnStart.visibility = View.VISIBLE
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is HideNavigation)
        {
            listener = context
        }
    }

}