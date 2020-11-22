package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.addrecipe

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.*
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

    private val recipeRepository = RecipeRepository(application)

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun insertRecipe(name: String, ingredients: String, steps: String, image: Bitmap) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val imageString = convertBitmap(image)
                recipe = Recipe(name = name, ingredients = ingredients, steps = steps, image = imageString)
                recipeRepository.insert(recipe)
            }
        }
    }

    private fun convertBitmap(bitmap: Bitmap): ByteArray {
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos)
        return bos.toByteArray()
    }
}