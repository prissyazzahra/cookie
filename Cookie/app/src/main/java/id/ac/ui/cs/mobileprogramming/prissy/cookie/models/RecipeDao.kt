package id.ac.ui.cs.mobileprogramming.prissy.cookie.models;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe ORDER BY name ASC")
    fun getAscRecipes(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipe: Recipe)
}
