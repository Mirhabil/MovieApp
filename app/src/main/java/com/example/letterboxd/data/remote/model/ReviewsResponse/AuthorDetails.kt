package com.example.letterboxd.data.remote.model.ReviewsResponse

data class AuthorDetails(
    val avatar_path: String,
    val name: String,
    val rating: Double,
    val username: String
)