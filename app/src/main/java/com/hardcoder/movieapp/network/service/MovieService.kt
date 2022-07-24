package com.hardcoder.movieapp.network.service

import com.hardcoder.movieapp.core.model.PopularResponse
import com.hardcoder.movieapp.core.model.MovieResponse
import com.hardcoder.movieapp.utils.Constants.API_KEY
import com.hardcoder.movieapp.utils.Constants.LANGUAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("language")
        language: String = LANGUAGE,
        @Query("page")
        page: Int = 1
    ): Response<PopularResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("language")
        language: String = LANGUAGE,
        @Query("page")
        page: Int = 1
    ): Response<MovieResponse>

}