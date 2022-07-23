package com.hardcoder.movieapp.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hardcoder.movieapp.core.model.PopularList
import com.hardcoder.movieapp.database.dao.MovieDao

@Database(
    entities = [PopularList::class],
    version = 1
)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}