package com.example.imagesearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    private val repository = PhotoRepository()

    // StateFlow to hold the current list of photos
    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos

    // StateFlow to emit any error messages
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun searchPhotos(query: String) {
        viewModelScope.launch {
            try {
                val result = repository.searchPhotos(query)
                if (result.isSuccess) {
                    _photos.value = result.getOrDefault(emptyList())
                } else {
                    throw result.exceptionOrNull() ?: Exception("Unknown error")
                }
            } catch (e: Exception) {
                // Handle error here: Log and set error message
                Log.e("PhotoViewModel", "Error searching photos", e)
                _errorMessage.value = "Error fetching images: ${e.message ?: "Unknown error"}"
            }
        }
    }
}
