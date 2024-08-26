package com.example.letterboxd.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PopularFilmsThisMonthModel(

    val image:String,
    val moviname:String,
    val movieDescription:String,
    val movieYear:String,
    val movieID:Int,
    val backdropPath:String,
    val rating:Double
):Parcelable