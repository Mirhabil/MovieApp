package com.example.letterboxd.data.remote.model.CreditsResponse

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)