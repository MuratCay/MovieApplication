package com.hardcoder.movieapp.data.repository

import com.hardcoder.movieapp.data.datasource.movie.local.MovieLocalDataSource
import com.hardcoder.movieapp.data.datasource.movie.remote.MovieRemoteDataSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    movieLocalDataSource: MovieLocalDataSource
) :
    MovieRepository {
    override suspend fun getPopularFromNetwork() = movieRemoteDataSource.getPopularFromNetwork()
}