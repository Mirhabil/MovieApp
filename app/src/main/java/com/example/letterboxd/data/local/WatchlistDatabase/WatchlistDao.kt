package com.example.letterboxd.data.local.WatchlistDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WatchlistDao {

    @Insert
    suspend fun insertPoster(poster: WatchlistEntity)

    @Query("SELECT * from WatchlistEntity")
    suspend fun getALLPosters():List<WatchlistEntity>

    @Delete
    suspend fun deletePoster(poster: WatchlistEntity)

    @Query("DELETE FROM WatchlistEntity WHERE id = :id")
    suspend fun deletePosterById(id:Int)

    @Query("DELETE FROM WatchlistEntity")
    suspend fun deleteAllRooms()

    @Query("SELECT COUNT(*) FROM WatchlistEntity WHERE id = :itemId")
    suspend fun exists(itemId: Int): Int
}