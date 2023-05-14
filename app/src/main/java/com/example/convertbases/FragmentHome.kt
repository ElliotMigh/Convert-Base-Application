package com.example.convertbases

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.convertbases.databinding.FragmentHomeBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import kotlin.math.pow

class FragmentHome : Fragment() {

    //Create binding
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //click on convert button (تبدیل کن)
        binding.btnConvert.setOnClickListener {
            if (binding.etFirstBasis.length() > 0 && binding.etSecondBasis.length() > 0 && binding.etNumber.length() > 0) {
                //get value from edit texes
                val firstBasis = binding.etFirstBasis.text.toString()
                val secondBasis = binding.etSecondBasis.text.toString()
                var myNumber = binding.etNumber.text.toString()
                if (firstBasis.toInt() >= 2 && firstBasis.toInt() <= 16 && secondBasis.toInt() >= 2 && secondBasis.toInt() <= 16) {
                    //convert bases :
                    var newNumber = 0
                    var i = 0
                    while (myNumber.toInt() > 0) {
                        var remainer = myNumber.toInt() % 10
                        newNumber += remainer * (firstBasis.toDouble().pow(i.toDouble()).toInt())
                        myNumber = (myNumber.toInt() / 10).toString()
                        i++
                    }
                    myNumber = newNumber.toString()
                    newNumber = 0
                    i = 1
                    while (myNumber.toInt() > 0) {
                        var remainer = myNumber.toInt() % secondBasis.toInt()
                        newNumber = (remainer * i) + newNumber
                        myNumber = (myNumber.toInt() / secondBasis.toInt()).toString()
                        i = i * 10
                    }
                    //set result on text view
                    binding.txtAnswer.text = newNumber.toString()
                } else {
                    Toast.makeText(
                        context,
                        "تبدیل مبناها باید بین ۲ تا ۱۶ باشد.دقت کن",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    context,
                    "لطفا موارد خواسته شده رو وارد کن دوست شیطون من :)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //click on delete button (پاک کن)
        binding.btnDelete.setOnClickListener {
            //call show custom dialog box function for delete data from fragment
            showCustomDialogBox()
        }

        //click on save button (ذخیره کن)
        binding.btnSave.setOnClickListener {
            // write permission to access the storage
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )

            val bitmap = getScreenShotFromView(binding.linearConvert)
            // if bitmap is not null then
            // save it to gallery
            if (bitmap != null) {
                saveMediaToStorage(bitmap)
            }
        }
    }

    private fun getScreenShotFromView(linearConvert: LinearLayout): Bitmap? {
        // create a bitmap object
        var screenshot: Bitmap? = null
        try {
            // inflate screenshot object
            // with Bitmap.createBitmap it
            // requires three parameters
            // width and height of the view and
            // the background color
            screenshot = Bitmap.createBitmap(
                requireView().measuredWidth,
                requireView().measuredHeight,
                Bitmap.Config.ARGB_8888
            )
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshot)
            requireView().draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Failed to capture screenshot because:" + e.message)
        }
        // return the bitmap
        return screenshot
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {
        // Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        // Output stream
        var fos: OutputStream? = null

        // For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            requireContext().contentResolver?.also { resolver ->

                // Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    // putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                // Inserting the contentValues to
                // contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                // Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            // These for devices running on android < Q
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(
                context,
                "عکس از صفحه گرفته شد.تقلب رو توی گالریت داری:)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun clearEditText() {
        //Clear edit text
        binding.etFirstBasis.text.clear()
        binding.etSecondBasis.text.clear()
        binding.etNumber.text.clear()
    }

    private fun showCustomDialogBox() {
        val dialogDelete = Dialog(requireContext())
        dialogDelete.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogDelete.setCancelable(false)
        dialogDelete.setContentView(R.layout.layout_custom_dialog)
        dialogDelete.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val buttonYes: Button = dialogDelete.findViewById(R.id.btnYesDelete)
        val buttonDelete: Button = dialogDelete.findViewById(R.id.btnNoDelete)

        //click on yes button
        buttonYes.setOnClickListener {
            //call clear edit text function
            clearEditText()
            Toast.makeText(context, "موارد وارد شده پاک شد!", Toast.LENGTH_SHORT).show()
            dialogDelete.dismiss()
        }
        //click on no button
        buttonDelete.setOnClickListener {
            dialogDelete.dismiss()
        }
        //show dialog
        dialogDelete.show()
    }
}