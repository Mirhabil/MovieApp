package com.example.letterboxd.data.local.ReviewsDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ReviewsEntity(

    @PrimaryKey (autoGenerate = true)val id:Int=0,

    val movieId:Int,
    val imageUrl:String,
    val review:String,
    val movieName:String,
    val movieYear:String,
    val rating:Double

)