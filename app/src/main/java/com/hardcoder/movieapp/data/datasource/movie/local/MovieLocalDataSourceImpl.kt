package com.hardcoder.movieapp.data.datasource.movie.local

import com.hardcoder.movieapp.database.dao.MovieDao
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(val dao: MovieDao) : MovieLocalDataSource {

}