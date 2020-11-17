package id.ac.ui.cs.mobileprogramming.prissy.cookie.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.RecipeDao

class RecipeRepository(private val recipeDao: RecipeDao) {
    val allRecipe: LiveData<List<Recipe>> = recipeDao.getAscRecipes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }
}