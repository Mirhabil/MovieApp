package com.example.letterboxd.data.remote.model.PopularMoviesResponse

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)