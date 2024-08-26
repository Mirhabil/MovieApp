package com.example.letterboxd.data.local.ReviewsDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReviewsDao {

    @Insert
    suspend fun insertPoster(poster: ReviewsEntity)

    @Query("SELECT * from ReviewsEntity")
    suspend fun getALLPosters():List<ReviewsEntity>

    @Delete
    suspend fun deletePoster(poster: ReviewsEntity)

    @Query("DELETE FROM ReviewsEntity WHERE id = :id")
    suspend fun deletePosterById(id:Int)

    @Query("DELETE FROM ReviewsEntity")
    suspend fun deleteAllRooms()

    @Query("SELECT COUNT(*) FROM ReviewsEntity WHERE id = :itemId")
    suspend fun exists(itemId: Int): Int
}