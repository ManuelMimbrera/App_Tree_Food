package com.example.practica1.ui.hardware

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.lang.Integer.min
import java.text.SimpleDateFormat
import java.util.*

class ImageCaptureHelper(private val context: HardwareActivity) {

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 0x100
    }

    var currentPhotoPath: String? = null

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        println(storageDir)

        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(context.packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }


                println("ccccccccccc")


                // Continue only if the File was successfully created
                photoFile?.also { file ->

                    println("dddddddddddddd")

                    println(file.absolutePath.toString())

                    val photoURI: Uri = FileProvider.getUriForFile(
                        context, "com.example.practica1.provider", file
                    )

                    print(photoURI.toString())

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    context.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    fun deletePhoto(filePath: String) {
        val myFile = File(filePath)
        if (myFile.exists()) myFile.delete()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadPhot(imageView: ImageView) {
        // Get the dimensions of the View
        val targetW: Int = imageView.width
        val targetH: Int = imageView.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap ->
            imageView.setImageBitmap(bitmap)
        }
    }


    fun deleteAllPhotos(excludeFile: String? = null) {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        storageDir?.also {
            for (f in storageDir.listFiles()) {
                if (f.isFile) {
                    if (f.absolutePath != excludeFile) {
                        f.delete()
                    }

                    /*if (f.name.startsWith("JPEG_")) { }*/
                }
            }

        }

    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            context.sendBroadcast(mediaScanIntent)
        }
    }



}