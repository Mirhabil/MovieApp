package com.example.letterboxd.data.remote

import com.example.letterboxd.data.local.ReviewsDatabase.ReviewsEntity
import com.example.letterboxd.domain.model.AllReviewsModel

fun ReviewsEntity.toAllReviewsModel():AllReviewsModel{

    return AllReviewsModel(
        profileImage = this.imageUrl,
        personName = "You",
        review=this.review,
        rating = 5.5

    )
}