package com.example.imagesearch

import android.util.Log

private const val CLIENT_ID = "NHr5nmnvy4fJA0AtfpReQm_EI2SBnnvPajDObRtmYbY"

class PhotoRepository {

    suspend fun searchPhotos(query: String): Result<List<Photo>> {
        return try {
            val response = RetrofitInstance.api.searchPhotos(query, CLIENT_ID)
            Result.success(response.results)
        } catch (e: Exception) {
            // Log the error and return failure with a message
            Log.e("PhotoRepository", "Error fetching photos", e)
            Result.failure(e)
        }
    }
}
