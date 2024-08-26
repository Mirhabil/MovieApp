package com.example.letterboxd.data.remote.model.ReviewsResponse

data class ReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)