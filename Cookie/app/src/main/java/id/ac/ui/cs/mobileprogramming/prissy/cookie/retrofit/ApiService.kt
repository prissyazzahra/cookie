package id.ac.ui.cs.mobileprogramming.prissy.cookie.retrofit

import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.RestaurantModel
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Venue
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/ad86e44d-cca7-4bc9-b5da-7957b68bbe25/")
    suspend fun getRestaurants(): Response<RestaurantModel>
}