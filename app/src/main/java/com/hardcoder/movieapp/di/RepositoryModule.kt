package com.hardcoder.movieapp.di

import com.hardcoder.movieapp.data.datasource.movie.local.MovieLocalDataSource
import com.hardcoder.movieapp.data.datasource.movie.remote.MovieRemoteDataSource
import com.hardcoder.movieapp.data.repository.MovieRepository
import com.hardcoder.movieapp.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }
}