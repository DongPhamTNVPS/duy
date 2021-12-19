package com.dong_pham.trafficsignrecognition.views

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.net.MailTo
import com.dong_pham.trafficsignrecognition.R
import kotlinx.android.synthetic.main.activity_home.*
import java.util.jar.Manifest

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // thư viện
        button   = findViewById(R.id.button2)
        imageView = findViewById(R.id.imageView)
        button.setOnClickListener {
            pickImageGallery()
        }

// chụp ảnh
        button.isEnabled = false
        if (ActivityCompat.checkSelfPermission(this , android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA) , 111)
        }
        else
            button.isEnabled = true
        button.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)

        }

    }

    private fun pickImageGallery() {

        var intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }






    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            var pic = data?.getParcelableExtra<Bitmap>("data")
            imageView.setImageBitmap(pic)
        }
        if (requestCode == IMAGE_REQUEST_CODE && requestCode == RESULT_OK  ){
            imageView.setImageURI(data?.data)
        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            button.isEnabled = true
        }
    }
    // thư viện
    private lateinit var button: Button
    private lateinit var imageView: ImageView


    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

}