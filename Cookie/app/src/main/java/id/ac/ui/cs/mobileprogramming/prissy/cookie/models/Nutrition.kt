package id.ac.ui.cs.mobileprogramming.prissy.cookie.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition")
data class Nutrition(
    @ColumnInfo(name = "food_name")
    var name: String,
    var calories: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}