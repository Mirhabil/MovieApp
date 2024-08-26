package com.example.letterboxd.data.local.WatchlistDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WatchlistEntity::class], version = 2)
abstract class WatchlistDatabase : RoomDatabase(){

    abstract fun getWatchListDao(): WatchlistDao
}