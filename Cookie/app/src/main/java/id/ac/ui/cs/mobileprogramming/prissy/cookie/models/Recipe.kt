package id.ac.ui.cs.mobileprogramming.prissy.cookie.models;

import androidx.room.*;

@Entity(tableName = "recipe")
data class Recipe(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "ingredients")
    var ingredients: String,

    @ColumnInfo(name = "steps")
    var steps: String,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray,

    @ColumnInfo(name = "portion")
    var portion: Int,

    @ColumnInfo(name = "category")
    var category: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    var id: Int = 0
}
