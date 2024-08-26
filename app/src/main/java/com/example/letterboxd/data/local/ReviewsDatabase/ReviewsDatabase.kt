package com.example.letterboxd.data.local.ReviewsDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ReviewsEntity::class], version = 1)
abstract class ReviewsDatabase():RoomDatabase() {

    abstract fun getReviewsDao():ReviewsDao

}