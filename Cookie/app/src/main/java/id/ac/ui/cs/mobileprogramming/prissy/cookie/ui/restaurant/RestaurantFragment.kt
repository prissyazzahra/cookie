package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.restaurant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R

class RestaurantFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: RestaurantViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java)
        val root = inflater.inflate(R.layout.restaurant_fragment, container, false)
        viewModel.getRestaurantList()
        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.restaurants.observe(viewLifecycleOwner, Observer {
            val adapter = RestaurantListAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        // TODO: Use the ViewModel
    }

}