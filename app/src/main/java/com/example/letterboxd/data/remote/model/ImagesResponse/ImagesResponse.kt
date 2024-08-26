package com.example.letterboxd.data.remote.model.ImagesResponse

data class ImagesResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)