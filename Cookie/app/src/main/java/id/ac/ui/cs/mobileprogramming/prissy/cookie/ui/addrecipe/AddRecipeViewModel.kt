package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.addrecipe

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.prissy.cookie.external.PortionCategory
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe
import id.ac.ui.cs.mobileprogramming.prissy.cookie.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*

class AddRecipeViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var recipe : Recipe
    private val categoryClass = PortionCategory()

    private val recipeRepository = RecipeRepository(application)

    fun insertRecipe(name: String, ingredients: String, steps: String, image: Bitmap, portion: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val imageString = convertBitmap(image)
                val categoryInt = categoryClass.categorizePortion(portion)
                val category = convertCategoryToString(categoryInt)
                recipe = Recipe(name = name, ingredients = ingredients, steps = steps, image = imageString, portion = portion, category = category)
                recipeRepository.insert(recipe)
            }
        }
    }

    private fun convertBitmap(bitmap: Bitmap): ByteArray {
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos)
        return bos.toByteArray()
    }

    private fun convertCategoryToString(int: Int): String {
        return when (int) {
            0 -> "Personal Portion"
            1 -> "Family Portion"
            else -> "Party Portion"
        }
    }
}