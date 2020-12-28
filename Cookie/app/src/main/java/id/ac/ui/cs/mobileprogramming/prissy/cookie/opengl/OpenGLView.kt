package id.ac.ui.cs.mobileprogramming.prissy.cookie.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import id.ac.ui.cs.mobileprogramming.prissy.cookie.opengl.OpenGLRenderer

class OpenGLView(context: Context) : GLSurfaceView(context) {
    private val glRenderer: OpenGLRenderer

    init {
        setEGLContextClientVersion(2)
        preserveEGLContextOnPause = true
        glRenderer =
            OpenGLRenderer()
        setRenderer(glRenderer)
    }
}