package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.restaurant

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonArray
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.RestaurantModel
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Venue
import id.ac.ui.cs.mobileprogramming.prissy.cookie.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {
    private val retrofit = RetrofitClient.RETROFIT_SERVICE
    val restaurants = MutableLiveData<RestaurantModel>()

    fun getRestaurantList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = retrofit.getRestaurants().body()
                restaurants.value = response
                Log.d("INFO", response.toString())
            }
            catch (e: Error) {
                Log.d("ERROR", "Error occurred")
            }
        }
    }
}