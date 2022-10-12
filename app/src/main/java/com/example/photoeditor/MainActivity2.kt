package com.example.photoeditor

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.photoeditor.databinding.ActivityMain2Binding
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        val byteArray = intent.getByteArrayExtra("image")
        val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        binding.imageView3.setImageBitmap(bmp)

        binding.imageButton.setOnClickListener {
            binding.imageView3.setRotation(binding.imageView3.rotation + 90F)

        }

        binding.imageButton2.setOnClickListener {
            startCropActivity()


        }

        binding.imageButton3.setOnClickListener {
//            binding.imageView3.setImageDrawable(null)
            if (binding.imageView3 == null){
            }

        }

        binding.imageButton4.setOnClickListener {
        }

    }

    private fun startCropActivity() {
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
            .start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val resultUri = result.uri
                binding.imageView3.setImageURI(resultUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }
}

