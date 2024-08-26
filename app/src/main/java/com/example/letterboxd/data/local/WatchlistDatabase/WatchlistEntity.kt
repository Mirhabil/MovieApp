package com.example.letterboxd.data.local.WatchlistDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchlistEntity(

    @PrimaryKey val id:Int,
    val imageUrl:String,
    val rating:Double,
    val movieName:String,
    val moiveYear:String
)