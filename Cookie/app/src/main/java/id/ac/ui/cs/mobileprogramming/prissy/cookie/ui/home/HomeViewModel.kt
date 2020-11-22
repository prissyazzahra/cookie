package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.home

import android.app.Application
import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe
import id.ac.ui.cs.mobileprogramming.prissy.cookie.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val recipes = MutableLiveData<List<Recipe>>();
    private var repository: RecipeRepository = RecipeRepository(application)

    fun getRecipes() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                var recipe: List<Recipe>
                withContext(Dispatchers.IO) {
                    recipe = repository.getAllRecipes()
                }
                recipes.value = recipe
            }
        }
    }
}