package com.example.letterboxd.data.remote.model.PeopleListsResponse

data class PeopleListsResponse(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)