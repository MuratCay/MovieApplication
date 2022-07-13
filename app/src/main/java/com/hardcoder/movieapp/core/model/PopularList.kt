package com.hardcoder.movieapp.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hardcoder.movieapp.utils.Constants
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = Constants.MOVIE_TABLE_NAME)
data class PopularList(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val id: Int,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
//    @Json(name = "genre_ids")
//    val genreIds: List<Int>,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
): Serializable