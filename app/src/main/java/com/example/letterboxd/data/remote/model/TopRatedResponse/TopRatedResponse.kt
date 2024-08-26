package com.example.letterboxd.data.remote.model.TopRatedResponse

data class TopRatedResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)