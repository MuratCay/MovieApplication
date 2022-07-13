package com.hardcoder.movieapp.di

import com.hardcoder.movieapp.data.repository.MovieRepository
import com.hardcoder.movieapp.data.repository.MovieRepositoryImpl
import com.hardcoder.movieapp.data.service.MovieService
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
    fun provideMovieRepository(apiService: MovieService): MovieRepository {
        return MovieRepositoryImpl(apiService = apiService)
    }
}