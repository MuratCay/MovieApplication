package com.hardcoder.movieapp.data.datasource.movie.remote

import com.hardcoder.movieapp.core.model.PopularResponse
import com.hardcoder.movieapp.utils.Resource

interface MovieRemoteDataSource {
    suspend fun getPopularFromNetwork(): Resource<PopularResponse>
}