package com.jkrj02.whattoeat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jkrj02.whattoeat.data.model.EatData

@Database(
    entities = [EatData::class],
    version = 1
)
abstract class EatDatabase:RoomDatabase() {
    abstract fun eatDao():EatDao

    //数据库实例只能有一个
    companion object
    {
        private var eatDatabase: EatDatabase? = null
        fun getEatDatabase(context: Context):EatDatabase
        {
            var tmp= eatDatabase
            if(tmp!=null)
            {
                return tmp
            }
            //如果没有实例，则创建一个
            tmp=Room.databaseBuilder(
                context.applicationContext,
                EatDatabase::class.java,
                "dataOfEat"
            ).build()
            eatDatabase=tmp
            return tmp
        }
    }
}