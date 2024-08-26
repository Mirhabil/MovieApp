package com.example.letterboxd.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RecentFriendsReviewModel(

    val profileImage:String,
    val postImage:String,
    val movieName:String,
    val movieYear:String,
    val movieDescription:String,
    val movieActor:String,
    val rating:Double,
    val movieId:Int
):Parcelable