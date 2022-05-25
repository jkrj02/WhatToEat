package com.jkrj02.whattoeat.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jkrj02.whattoeat.data.EatDao
import com.jkrj02.whattoeat.data.EatDatabase
import com.jkrj02.whattoeat.data.model.EatData
import com.jkrj02.whattoeat.repository.EatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EatViewModel(app: Application) : AndroidViewModel(app) {
    private val eatDao : EatDao = EatDatabase.getEatDatabase(app).eatDao()
    private val repository: EatRepository = EatRepository(eatDao)

    val allData:LiveData<List<EatData>> = repository.getAll

    fun insert(myData: EatData)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.insert(myData)
        }
    }
    fun delete(myData: EatData)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.delete(myData)
        }
    }
    fun update(myData: EatData)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.update(myData)
        }
    }
    fun getById(id: Int) : EatData
    {
        return repository.getById(id)
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
    fun countByName(name:String):Int
    {
        return repository.countByName(name)
    }
}