package com.example.letterboxd.data.local.ImageDatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class PosterEntity(
    @PrimaryKey val id:Int,
    val imageURL:String
):Parcelable