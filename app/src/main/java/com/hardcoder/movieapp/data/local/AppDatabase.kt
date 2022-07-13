package com.hardcoder.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hardcoder.movieapp.core.model.PopularList

@Database(
    entities = [PopularList::class],
    version = 1
)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}