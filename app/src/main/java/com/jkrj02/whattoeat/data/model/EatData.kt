package com.jkrj02.whattoeat.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "eatData")
data class EatData (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var FoodNumber: Int, //食堂中菜品的数量
):Parcelable