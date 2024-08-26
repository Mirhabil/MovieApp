package com.example.letterboxd.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PopularListsThisMonthModel(

    val classImage1:String,
    val movieId1:Int,
    val classImage2:String,
    val movieId2:Int,
    val classImage3:String,
    val movieId3:Int,
    val classImage4:String,
    val movieId4:Int
):Parcelable