package com.jkrj02.whattoeat.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jkrj02.whattoeat.data.FoodDao
import com.jkrj02.whattoeat.data.FoodDatabase
import com.jkrj02.whattoeat.data.model.FoodData
import com.jkrj02.whattoeat.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(app: Application) : AndroidViewModel(app) {
    private val foodDao : FoodDao = FoodDatabase.getFoodDatabase(app).foodDao()
    private val repository: FoodRepository = FoodRepository(foodDao)

    val allData:LiveData<List<FoodData>> = repository.getAll

    fun insert(myData: FoodData)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insert(myData)
        }
    }
    fun delete(myData: FoodData)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.delete(myData)
        }
    }
    fun update(myData: FoodData)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.update(myData)
        }
    }
    fun getById(id: Int) : FoodData
    {
        return repository.getById(id)
    }
    fun getAllByPacker(packer : Int) : LiveData<List<FoodData>>
    {
        return repository.getAllByPacker(packer)
    }
    fun getAllByPackerForDelete(packer : Int) : List<FoodData>
    {
        return repository.getAllByPackerForDelete(packer)
    }
    fun getNumber() : Int
    {
        return repository.getNumber()
    }
    fun getMinId() : Int
    {
        return repository.getMinId()
    }
    fun getMaxId() : Int
    {
        return repository.getMaxId()
    }
    fun countById(id:Int) : Int
    {
        return repository.countById(id)
    }
}