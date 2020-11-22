package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.addnutrition

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.addrecipe.AddRecipeViewModel

class AddNutritionFragment : Fragment() {

    companion object {
        fun newInstance() = AddNutritionFragment()
    }

    private lateinit var viewModel: AddNutritionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar?.setTitle("Add Nutrition")
        viewModel = ViewModelProvider(requireActivity()).get(AddNutritionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_nutrition, container, false)
        return root
    }

}
