package com.example.letterboxd.data.local.ImageDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PosterDAO {

    @Insert
    suspend fun insertPoster(poster: PosterEntity)

    @Query("SELECT * from PosterEntity")
    suspend fun getALLPosters():List<PosterEntity>

    @Delete
    suspend fun deletePoster(poster: PosterEntity)

    @Query("DELETE FROM PosterEntity WHERE id = :id")
    suspend fun deletePosterById(id:Int)

    @Query("DELETE FROM PosterEntity")
    suspend fun deleteAllRooms()

    @Query("SELECT COUNT(*) FROM PosterEntity WHERE id = :itemId")
    suspend fun exists(itemId: Int): Int
}