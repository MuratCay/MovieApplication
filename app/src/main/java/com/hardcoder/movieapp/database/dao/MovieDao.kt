package com.hardcoder.movieapp.database.dao

import androidx.room.*
import com.hardcoder.movieapp.core.model.PopularList

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: PopularList)

    @Delete
    suspend fun removeMovie(movie: PopularList)

    @Query("SELECT * FROM movie_table")
    suspend fun getFavoriteMovies(): List<PopularList>

    @Query("SELECT * FROM movie_table where id like :id")
    suspend fun getMovieById(id: String): PopularList
}