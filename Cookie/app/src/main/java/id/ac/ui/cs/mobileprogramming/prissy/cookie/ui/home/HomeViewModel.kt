package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe
import id.ac.ui.cs.mobileprogramming.prissy.cookie.repository.RecipeRepository

class HomeViewModel(private val repository: RecipeRepository) : ViewModel() {
    val allRecipe: LiveData<List<Recipe>> = repository.allRecipe
}

class HomeViewModelFactory(private val repository: RecipeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}