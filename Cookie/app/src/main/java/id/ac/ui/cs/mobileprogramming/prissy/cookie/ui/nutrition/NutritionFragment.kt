package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.nutrition

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R


class NutritionFragment : Fragment() {

    companion object {
        fun newInstance() =
            NutritionFragment()
    }

    private lateinit var viewModel: NutritionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nutrition, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NutritionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
