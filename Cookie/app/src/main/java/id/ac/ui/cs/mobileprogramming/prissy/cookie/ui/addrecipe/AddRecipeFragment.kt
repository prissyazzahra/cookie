package id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.addrecipe

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.prissy.cookie.R
import id.ac.ui.cs.mobileprogramming.prissy.cookie.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_add_recipe.*


class AddRecipeFragment : Fragment() {

    private lateinit var addRecipeViewModel: AddRecipeViewModel
    private lateinit var imageUri: Uri
    private lateinit var bitmap: Bitmap
    private val pickImage = 100
    private val PICK_FROM_GALLERY = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar?.setTitle("Add Recipe")
        addRecipeViewModel =
            ViewModelProvider(requireActivity()).get(AddRecipeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_recipe, container, false)

        val chooseImageButton: Button = root.findViewById(R.id.chooseImageButton)
        val saveButton: Button = root.findViewById(R.id.save_button)
        val name: EditText = root.findViewById(R.id.name_input)
        val ingredients: EditText = root.findViewById(R.id.ing_input)
        val steps: EditText = root.findViewById(R.id.step_input)
        val portion: EditText = root.findViewById(R.id.portion_input)

        chooseImageButton.setOnClickListener {
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
            Log.d("INFO", "ke klik kok")
        }
        saveButton.setOnClickListener {
            addRecipeViewModel.insertRecipe(image = bitmap,
                name = name.text.toString(),
                ingredients = ingredients.text.toString(),
                steps = steps.text.toString(),
                portion = portion.text.toString().toInt()
            )
            Toast.makeText(requireContext(), "Recipe added!", Toast.LENGTH_SHORT).show()
            val frag = parentFragmentManager
            frag.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment()).commit()
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data!!
            bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver!!, imageUri)
            image.setImageBitmap(bitmap)
            image.setImageURI(imageUri)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PICK_FROM_GALLERY -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, pickImage)
            } else if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                builder.setTitle(R.string.title_permission)
                builder.setMessage(R.string.desc_permission)
                builder.setPositiveButton(R.string.settings) { dialog, which ->
                    dialog.cancel()
                    openSettings()
                }
                builder.setNegativeButton(
                    getString(android.R.string.cancel)
                ) { dialog, which -> dialog.cancel() }
                builder.show()
            }
            else -> {
                Toast.makeText(
                    requireContext(),
                    "Can't choose photo from gallery",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", activity?.packageName, null)
        intent.data = uri
        requireContext().startActivity(intent)
    }
}
