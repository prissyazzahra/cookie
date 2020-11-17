package id.ac.ui.cs.mobileprogramming.prissy.cookie.models;

import androidx.room.*;

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "ingredients")
    val ingredients: String,

    @ColumnInfo(name = "steps")
    val steps: String,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray
)
