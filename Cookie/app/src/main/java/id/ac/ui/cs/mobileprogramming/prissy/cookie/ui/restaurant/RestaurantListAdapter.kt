package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.restaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.RestaurantModel
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Venue

class RestaurantListAdapter(internal var context: Context, internal var list: RestaurantModel) : RecyclerView.Adapter<RestaurantListAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var type: TextView = itemView.findViewById(R.id.type)
        var rating: TextView = itemView.findViewById(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListAdapter.ListViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.restaurants?.size!!
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.name.text = list.restaurants?.get(position)?.restaurantName
        holder.type.text = list.restaurants?.get(position)?.restaurantType
        holder.rating.text = list.restaurants?.get(position)?.restaurantRate.toString()
    }
}