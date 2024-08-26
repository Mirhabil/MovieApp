package com.example.letterboxd.data.remote.model.DetailsResponse

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)