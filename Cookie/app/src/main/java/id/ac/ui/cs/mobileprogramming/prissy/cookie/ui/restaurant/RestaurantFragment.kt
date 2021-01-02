package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.restaurant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.utils.ConnectionUtil
import java.lang.Error

class RestaurantFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: RestaurantViewModel
    private val util = ConnectionUtil()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(requireActivity()).get(RestaurantViewModel::class.java)
        val root = inflater.inflate(R.layout.restaurant_fragment, container, false)

        if (!util.isConnected(requireContext())) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            builder.setTitle(R.string.title_connection)
            builder.setMessage(R.string.desc_connection)
            builder.setNegativeButton(
                getString(android.R.string.ok)
            ) { dialog, _ -> dialog.cancel() }
            builder.show()
        } else {
            viewModel.getRestaurantList()
        }

        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.restaurants.observe(viewLifecycleOwner, Observer {
            val adapter = RestaurantListAdapter(requireContext(), it)
            recyclerView.adapter = adapter
        })
        return root
    }

}