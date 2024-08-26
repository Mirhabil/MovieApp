package com.example.letterboxd.data.local.ImageDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PosterEntity::class], version = 2)
abstract class ImageDatabase :RoomDatabase(){

    abstract fun getPosterDAO(): PosterDAO
}