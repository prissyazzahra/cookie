package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.aboutus

import android.content.Intent
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.prissy.cookie.MainActivity
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.opengl.OpenGLRenderer
import id.ac.ui.cs.mobileprogramming.prissy.cookie.opengl.OpenGLView


class AboutUsActivity : AppCompatActivity() {
    private lateinit var openGLView : OpenGLView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        openGLView = findViewById(R.id.oglView)
        openGLView.init()
        openGLView.setRenderer(OpenGLRenderer())
    }

    override fun onResume() {
        super.onResume()
        openGLView.onResume()
    }

    override fun onPause() {
        super.onPause()
        openGLView.onPause()
    }

    override fun onBackPressed() {
        val k = Intent(this@AboutUsActivity, MainActivity::class.java)
        startActivity(k)
    }
}