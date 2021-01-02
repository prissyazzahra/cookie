package id.ac.ui.cs.mobileprogramming.prissy.cookie.models

import com.google.gson.annotations.SerializedName

class RestaurantModel {
    @SerializedName("venues")
    var restaurants: List<Venue>? = null
}

class Venue {
    @SerializedName("name")
    var restaurantName: String? = null

    @SerializedName("type")
    var restaurantType: String? = null

    @SerializedName("rating")
    var restaurantRate: Float? = null
}