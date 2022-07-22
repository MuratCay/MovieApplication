package com.hardcoder.movieapp.di

import com.hardcoder.movieapp.data.datasource.movie.local.MovieLocalDataSource
import com.hardcoder.movieapp.data.datasource.movie.local.MovieLocalDataSourceImpl
import com.hardcoder.movieapp.data.datasource.movie.remote.MovieRemoteDataSource
import com.hardcoder.movieapp.data.datasource.movie.remote.MovieRemoteDataSourceImpl
import com.hardcoder.movieapp.database.dao.MovieDao
import com.hardcoder.movieapp.network.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(movieService: MovieService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(movieService)


    @Provides
    @Singleton
    fun provideLocalDataSource(dao: MovieDao): MovieLocalDataSource = MovieLocalDataSourceImpl(dao)
}