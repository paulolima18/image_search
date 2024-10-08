package com.example.imagesearch

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class PhotoDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        val photoUrl = intent.getStringExtra("photo_url")
        val imageView = findViewById<ImageView>(R.id.imageViewFull)

        Glide.with(this).load(photoUrl).into(imageView)
    }
}
