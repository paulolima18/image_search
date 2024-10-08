package com.example.imagesearch

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("client_id") clientId: String
    ): SearchResponse
}