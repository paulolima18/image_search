package com.example.imagesearch

data class Photo(
    val id: String,
    val urls: Urls,
    val user: User
)

data class Urls(
    val small: String,
    val full: String
)

data class User(
    val name: String
)

data class SearchResponse(
    val results: List<Photo>
)