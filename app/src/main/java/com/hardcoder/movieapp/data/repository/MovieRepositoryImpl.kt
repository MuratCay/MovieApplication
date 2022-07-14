package com.hardcoder.movieapp.data.repository

import com.hardcoder.movieapp.core.model.PopularResponse
import com.hardcoder.movieapp.data.service.MovieService
import com.hardcoder.movieapp.utils.Resource
import com.hardcoder.movieapp.utils.getResourceByNetworkRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: MovieService) :
    MovieRepository {

    override suspend fun getPopularFromNetwork() =
        getResourceByNetworkRequest { apiService.getPopular() }
}