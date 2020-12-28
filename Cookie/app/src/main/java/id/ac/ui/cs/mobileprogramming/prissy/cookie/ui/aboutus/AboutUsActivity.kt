package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.aboutus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.opengl.OpenGLView

class AboutUsActivity : AppCompatActivity() {
    private lateinit var openGLView : OpenGLView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openGLView = OpenGLView(this)
        setContentView(openGLView)
    }

    override fun onResume() {
        super.onResume()
        openGLView.onResume()
    }

    override fun onPause() {
        super.onPause()
        openGLView.onPause()
    }
}