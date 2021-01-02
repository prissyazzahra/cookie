package id.ac.ui.cs.mobileprogramming.prissy.cookie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.RecipeDao

@Database(entities = [Recipe::class], version = 3)
abstract class CookieDb : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: CookieDb? = null

        fun getDatabase(context: Context): CookieDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CookieDb::class.java,
                    "cookie_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}