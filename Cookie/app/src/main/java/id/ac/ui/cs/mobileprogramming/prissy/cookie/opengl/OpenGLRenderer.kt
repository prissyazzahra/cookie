package id.ac.ui.cs.mobileprogramming.prissy.cookie.opengl

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kotlin.math.sin


class OpenGLRenderer : GLSurfaceView.Renderer {
    var green = 1.1f
    var red = 1f
    var blue = 0f

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClearColor(red, green, blue, 1f)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        green = (sin(System.currentTimeMillis() * 2 * Math.PI / 3000) * 0.4 + 1).toFloat()
        red = (sin(System.currentTimeMillis() * 2 * Math.PI / 2000) * 0.4 + 1).toFloat()
        blue = (sin(System.currentTimeMillis() * 2 * Math.PI / 1000) * 0.4 + 1).toFloat()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES20.glClearColor(1.0f, green, 0.0f, 1f)
    }

}