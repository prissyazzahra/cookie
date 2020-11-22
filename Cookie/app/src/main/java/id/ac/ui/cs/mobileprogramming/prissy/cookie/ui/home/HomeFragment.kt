package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.models.Recipe
import id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.addrecipe.AddRecipeFragment

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val button = root.findViewById<FloatingActionButton>(R.id.fab)

        button.setOnClickListener{
            val fragment = AddRecipeFragment()
            val parentManager = this.parentFragmentManager.beginTransaction()
            parentManager.replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }

        val viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            System.out.println(it)
            val adapter = RecipeListAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })
        return root
    }
}
