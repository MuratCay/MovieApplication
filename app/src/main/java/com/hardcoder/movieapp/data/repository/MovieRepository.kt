package com.hardcoder.movieapp.data.repository

import com.hardcoder.movieapp.core.model.PopularResponse
import com.hardcoder.movieapp.core.model.UpcomingResponse
import com.hardcoder.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularFromNetwork(): Resource<PopularResponse>
    suspend fun getUpcomingFromNetwork(): Resource<UpcomingResponse>
}