package com.jkrj02.whattoeat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jkrj02.whattoeat.data.model.FoodData

@Database(
    entities = [FoodData::class],
    version = 1
)
abstract class FoodDatabase:RoomDatabase() {
    abstract fun foodDao():FoodDao

    //数据库实例只能有一个
    companion object
    {
        private var foodDatabase: FoodDatabase? = null
        fun getFoodDatabase(context: Context):FoodDatabase
        {
            var tmp= foodDatabase
            if(tmp!=null)
            {
                return tmp
            }
            //如果没有实例，则创建一个
            tmp=Room.databaseBuilder(
                context.applicationContext,
                FoodDatabase::class.java,
                "dataOfFood"
            ).build()
            foodDatabase=tmp
            return tmp
        }
    }
}