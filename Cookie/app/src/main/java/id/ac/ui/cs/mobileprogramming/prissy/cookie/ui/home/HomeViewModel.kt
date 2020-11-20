package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.home

import android.app.Application
import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe
import id.ac.ui.cs.mobileprogramming.prissy.cookie.repository.RecipeRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val recipes = MutableLiveData<List<Recipe>>();
    private var repository: RecipeRepository = RecipeRepository(application)

    fun getRecipes() {
        viewModelScope.launch {
            recipes.value = repository.getAllRecipes()
        }
    }
}