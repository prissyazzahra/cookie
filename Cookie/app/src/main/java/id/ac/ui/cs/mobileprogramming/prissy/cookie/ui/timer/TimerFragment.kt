package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R

class TimerFragment : Fragment() {

    private lateinit var timerViewModel: TimerViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        timerViewModel =
                ViewModelProviders.of(this).get(TimerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_timer, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        timerViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
