package id.ac.ui.cs.mobileprogramming.prissy.cookie.repository

import android.app.Application
import id.ac.ui.cs.mobileprogramming.prissy.cookie.db.CookieDb
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Nutrition
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.NutritionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NutritionRepository(application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var nutritionDao: NutritionDao

    init {
        val db = CookieDb.getDatabase(application)
        nutritionDao = db.nutritionDao()
    }

    suspend fun getAllNutrition(): List<Nutrition> {
        var nutritions: List<Nutrition>
        withContext(Dispatchers.IO) {
            nutritions = nutritionDao.getAllNutritions()
        }
        return nutritions
    }

    suspend fun insertNutrition(nutrition: Nutrition) {
        withContext(Dispatchers.IO) {
            nutritionDao.insertNutrition(nutrition)
        }
    }
}