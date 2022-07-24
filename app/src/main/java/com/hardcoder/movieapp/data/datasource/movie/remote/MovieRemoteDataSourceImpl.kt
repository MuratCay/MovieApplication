package com.hardcoder.movieapp.data.datasource.movie.remote

import com.hardcoder.movieapp.core.model.PopularResponse
import com.hardcoder.movieapp.core.model.MovieResponse
import com.hardcoder.movieapp.network.service.MovieService
import com.hardcoder.movieapp.utils.Resource
import com.hardcoder.movieapp.utils.getResourceByNetworkRequest
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiService: MovieService) :
    MovieRemoteDataSource {
    override suspend fun getPopularFromNetwork(): Resource<PopularResponse> =
        getResourceByNetworkRequest { apiService.getPopular() }

    override suspend fun getUpcomingFromNetwork(): Resource<MovieResponse> =
        getResourceByNetworkRequest { apiService.getUpcoming() }

}