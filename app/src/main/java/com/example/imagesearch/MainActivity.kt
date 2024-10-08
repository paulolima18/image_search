package com.example.imagesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PhotoViewModel
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[PhotoViewModel::class.java]
        adapter = PhotoAdapter { photo ->
            val intent = Intent(this, PhotoDetailActivity::class.java)
            intent.putExtra("photo_url", photo.urls.full)
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.recyclerViewImages).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)  // 2 columns
            adapter = this@MainActivity.adapter
        }

        // Collect the StateFlow for photos
        lifecycleScope.launch {
            viewModel.photos.collect { photos ->
                adapter.submitList(photos)
            }
        }

        // Collect the StateFlow for errors
        lifecycleScope.launch {
            viewModel.errorMessage.collect { errorMessage ->
                errorMessage?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                }
            }
        }

        // Handle the search button click
        findViewById<Button>(R.id.buttonSearch).setOnClickListener {
            val query = findViewById<EditText>(R.id.editTextSearch).text.toString()
            if (query.isNotBlank()) {
                viewModel.searchPhotos(query)
            } else {
                Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


