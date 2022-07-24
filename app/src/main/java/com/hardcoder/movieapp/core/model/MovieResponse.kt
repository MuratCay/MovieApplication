package com.hardcoder.movieapp.core.model

import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "dates")
    val dates: Dates,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<AllMovieFeaturesResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)