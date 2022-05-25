package com.jkrj02.whattoeat.repository

import androidx.lifecycle.LiveData
import com.jkrj02.whattoeat.data.EatDao
import com.jkrj02.whattoeat.data.model.EatData

class EatRepository(private val eatDao: EatDao) {
    val getAll:LiveData<List<EatData>> = eatDao.getAll()

    suspend fun insert(myData: EatData)
    {
        eatDao.insert(myData)
    }
    suspend fun delete(myData: EatData)
    {
        eatDao.delete(myData)
    }
    suspend fun update(myData: EatData)
    {
        eatDao.update(myData)
    }
    fun getById(id: Int) : EatData
    {
        return eatDao.getById(id)
    }
    fun getNumber() : Int
    {
        return eatDao.getNumber()
    }
    fun getMinId() : Int
    {
        return eatDao.getMinId()
    }
    fun getMaxId() : Int
    {
        return eatDao.getMaxId()
    }
    fun countById(id:Int) : Int
    {
        return eatDao.countById(id)
    }
    fun countByName(name:String):Int
    {
        return eatDao.countByName(name)
    }
}