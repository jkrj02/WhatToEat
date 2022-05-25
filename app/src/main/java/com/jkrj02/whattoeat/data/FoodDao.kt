package com.jkrj02.whattoeat.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jkrj02.whattoeat.data.model.FoodData

@Dao
interface FoodDao {
    @Insert
    fun insert(myData: FoodData)

    @Delete
    fun delete(myData: FoodData)

    @Update
    fun update(myData: FoodData)

    @Query("select * from foodData")
    fun getAll(): LiveData<List<FoodData>>

    @Query("select * from foodData where id=:id")
    fun getById(id:Int):FoodData

    @Query("select * from foodData where packer=:packer")
    fun getAllByPacker(packer : Int) : LiveData<List<FoodData>>

    @Query("select * from foodData where packer=:packer")
    fun getAllByPackerForDelete(packer : Int) : List<FoodData>

    @Query("select count(*) from foodData")
    fun getNumber() : Int

    @Query("select min(id) from foodData")
    fun getMinId() : Int

    @Query("select max(id) from foodData")
    fun getMaxId() : Int

    @Query("select count(*) from foodData where id=:id")
    fun countById(id:Int) : Int

}