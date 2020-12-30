package id.ac.ui.cs.mobileprogramming.prissy.cookie.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import id.ac.ui.cs.mobileprogramming.prissy.cookie.opengl.OpenGLRenderer

class OpenGLView : GLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    fun init() {
        setEGLContextClientVersion(2)
        preserveEGLContextOnPause = true
    }
}