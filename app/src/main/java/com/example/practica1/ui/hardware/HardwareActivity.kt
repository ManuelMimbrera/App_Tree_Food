package com.example.practica1.ui.hardware

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.example.practica1.Login
import com.example.practica1.MainActivity
import com.example.practica1.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HardwareActivity : AppCompatActivity() {
    private val imageCaptureHelper by lazy {
        ImageCaptureHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hardware)


        val cam1 = findViewById<Button>(R.id.buttonCam1)


        cam1.setOnClickListener {

            imageCaptureHelper.takePhoto()
            //imageCaptureHelper.galleryAddPic()
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ImageCaptureHelper.REQUEST_IMAGE_CAPTURE) {

            if (resultCode == RESULT_OK) {

                imageCaptureHelper.deleteAllPhotos(excludeFile = imageCaptureHelper.currentPhotoPath)
                var imagen = findViewById<ImageView>(R.id.imageView)
                imageCaptureHelper.loadPhot(imagen)

            } else if (resultCode == RESULT_CANCELED) {

                imageCaptureHelper.currentPhotoPath?.also { fileName ->
                    imageCaptureHelper.deletePhoto(fileName)
                }

            }

        }
    }


}