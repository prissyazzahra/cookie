package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe

class RecipeListAdapter : RecyclerView.Adapter<RecipeListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    private var listRecipes: List<Recipe> = listOf()

    interface OnItemClickCallback {
        fun onItemClicked(data: Recipe, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ListViewHolder(itemView)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int){
        val currentRecipes = listRecipes.get(position)
        holder.name.setText(currentRecipes.name)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listRecipes[holder.adapterPosition], position)
        }
    }

    fun setRecipes(recipes: List<Recipe>) {
        listRecipes = recipes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listRecipes.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
    }
}