package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini adalah Timer"
    }
    val text: LiveData<String> = _text
}