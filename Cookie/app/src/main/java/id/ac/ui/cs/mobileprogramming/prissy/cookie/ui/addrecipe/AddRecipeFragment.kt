package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.addrecipe

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.prissy.cookie.MainActivity
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import kotlinx.android.synthetic.main.fragment_add_recipe.*


class AddRecipeFragment : Fragment() {

    private lateinit var addRecipeViewModel: AddRecipeViewModel
    private val pickImage = 100
    private var imageUri: Uri? = null
    private val PICK_FROM_GALLERY = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar?.setTitle("Add Recipe")
        addRecipeViewModel =
            ViewModelProviders.of(this).get(AddRecipeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_recipe, container, false)
        val button: Button = root.findViewById(R.id.chooseImageButton)

        button.setOnClickListener {
            if (context?.let { it1 ->
                    ContextCompat.checkSelfPermission(
                        it1, Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                } != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PICK_FROM_GALLERY)
            } else {
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            }
        }

        val textView: TextView = root.findViewById(R.id.text_notifications)
        addRecipeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            image.setImageURI(imageUri)
        }
    }
}
