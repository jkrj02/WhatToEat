package com.jkrj02.whattoeat.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jkrj02.whattoeat.data.model.EatData

@Dao
interface EatDao {
    @Insert
    fun insert(myData: EatData)

    @Delete
    fun delete(myData: EatData)

    @Update
    fun update(myData: EatData)

    @Query("select * from eatData")
    fun getAll(): LiveData<List<EatData>>

    @Query("select * from eatData where id=:id")
    fun getById(id:Int):EatData

    @Query("select count(*) from eatData")
    fun getNumber() : Int

    @Query("select min(id) from eatData")
    fun getMinId() : Int

    @Query("select max(id) from eatData")
    fun getMaxId() : Int

    @Query("select count(*) from eatData where id=:id")
    fun countById(id:Int) : Int

    @Query("select count(*) from eatData where name=:name")
    fun countByName(name: String):Int

}
