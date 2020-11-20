package id.ac.ui.cs.mobileprogramming.prissy.cookie.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.prissy.cookie.db.CookieDb
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.RecipeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class RecipeRepository(application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var recipeDao: RecipeDao

    init {
        val db = CookieDb.getDatabase(application)
        recipeDao = db.recipeDao()
    }

    suspend fun insert(recipe: Recipe) {
        withContext(Dispatchers.IO) {
            recipeDao.insertRecipe(recipe)
        }
    }

    suspend fun getAllRecipes(): List<Recipe> {
        var recipes: List<Recipe>
        withContext(Dispatchers.IO) {
            recipes = recipeDao.getAscRecipes()
        }
        return recipes
    }

    suspend fun getRecipe(id: Int): Recipe {
        var recipe: Recipe
        withContext(Dispatchers.IO) {
            recipe = recipeDao.getRecipeById(id)
        }
        return recipe
    }
}