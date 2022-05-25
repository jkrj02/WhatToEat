package com.jkrj02.whattoeat.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "foodData")
data class FoodData (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var packer: Int, //从属的食堂
): Parcelable