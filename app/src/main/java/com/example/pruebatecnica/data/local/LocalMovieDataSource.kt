package com.example.pruebatecnica.data.local

import com.example.pruebatecnica.data.model.MovieListModelEntity
import com.example.pruebatecnica.data.model.toMovieListModel

class LocalMovieDataSource(private val movieDao: MovieDao) {
    suspend fun getMovies() = movieDao.getAllMovies().toMovieListModel()
    suspend fun saveMovies(movieList: MovieListModelEntity) = movieDao.insertAllMovies(movieList)
}