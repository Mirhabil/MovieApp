package com.example.letterboxd.data.remote.model.SearchResponse

data class SearchResponse(
    val page: Int,
    val results: List<ResultSearch>,
    val total_pages: Int,
    val total_results: Int
)