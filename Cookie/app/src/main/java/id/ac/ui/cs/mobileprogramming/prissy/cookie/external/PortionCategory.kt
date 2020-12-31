package id.ac.ui.cs.mobileprogramming.prissy.cookie.external

class PortionCategory {
    @Throws(IllegalArgumentException::class)
    external fun categorizePortion(portion: Int): Int

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}