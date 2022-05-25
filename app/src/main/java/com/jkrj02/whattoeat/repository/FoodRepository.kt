package com.jkrj02.whattoeat.repository

import androidx.lifecycle.LiveData
import com.jkrj02.whattoeat.data.FoodDao
import com.jkrj02.whattoeat.data.model.FoodData

class FoodRepository(private val foodDao: FoodDao) {
    val getAll:LiveData<List<FoodData>> = foodDao.getAll()

    suspend fun insert(myData: FoodData)
    {
        foodDao.insert(myData)
    }
    suspend fun delete(myData: FoodData)
    {
        foodDao.delete(myData)
    }
    suspend fun update(myData: FoodData)
    {
        foodDao.update(myData)
    }
    fun getById(id: Int) : FoodData
    {
        return foodDao.getById(id)
    }
    fun getAllByPacker(packer : Int) : LiveData<List<FoodData>>
    {
        return foodDao.getAllByPacker(packer)
    }
    fun getAllByPackerForDelete(packer : Int) : List<FoodData>
    {
        return foodDao.getAllByPackerForDelete(packer)
    }
    fun getNumber() : Int
    {
        return foodDao.getNumber()
    }
    fun getMinId() : Int
    {
        return foodDao.getMinId()
    }
    fun getMaxId() : Int
    {
        return foodDao.getMaxId()
    }
    fun countById(id:Int) : Int
    {
        return foodDao.countById(id)
    }
}