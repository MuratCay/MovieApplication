package com.hardcoder.movieapp.data.repository

import com.hardcoder.movieapp.core.model.PopularResponse
import com.hardcoder.movieapp.core.model.MovieResponse
import com.hardcoder.movieapp.utils.Resource

interface MovieRepository {
    suspend fun getPopularFromNetwork(): Resource<PopularResponse>
    suspend fun getUpcomingFromNetwork(): Resource<MovieResponse>
}