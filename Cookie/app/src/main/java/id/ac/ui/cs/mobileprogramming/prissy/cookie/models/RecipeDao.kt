package id.ac.ui.cs.mobileprogramming.prissy.cookie.models;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe ORDER BY name ASC")
    suspend fun getAscRecipes(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe WHERE recipe_id=:id")
    suspend fun getRecipeById(id: Int): Recipe
}
