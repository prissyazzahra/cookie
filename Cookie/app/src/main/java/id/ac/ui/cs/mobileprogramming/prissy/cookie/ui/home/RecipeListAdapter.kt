package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe


class RecipeListAdapter(internal var context: Context, internal var list: List<Recipe>) : RecyclerView.Adapter<RecipeListAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var image: ImageView = itemView.findViewById(R.id.image)
        var portion: TextView = itemView.findViewById(R.id.portion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val bmp = BitmapFactory.decodeByteArray(list[position].image, 0, list[position].image.size)
        holder.name.text = list[position].name
        holder.image.setImageBitmap(Bitmap.createBitmap(bmp))
        holder.portion.text = list[position].category
    }
}