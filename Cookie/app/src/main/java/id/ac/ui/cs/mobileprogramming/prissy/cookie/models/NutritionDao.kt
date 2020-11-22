package id.ac.ui.cs.mobileprogramming.prissy.cookie.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NutritionDao {
    @Query("SELECT * FROM nutrition")
    suspend fun getAllNutritions(): List<Nutrition>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNutrition(nutrition: Nutrition)
}