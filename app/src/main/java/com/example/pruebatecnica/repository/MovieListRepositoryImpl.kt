package com.example.pruebatecnica.repository

import com.example.pruebatecnica.data.local.LocalMovieDataSource
import com.example.pruebatecnica.data.model.toMovieListModelEntity
import com.example.pruebatecnica.data.remote.MovieDataSource
import com.example.pruebatecnica.network.response.MovieListResponse
import retrofit2.Response

class MovieListRepositoryImpl(private val movieDataSource: MovieDataSource, private val movieDataSourceLocal:LocalMovieDataSource):MovieListRepository {
    override suspend fun getMovie(): MovieListResponse {
        return try {
            movieDataSource.getMovies().results.forEach {
                movieDataSourceLocal.saveMovies(it.toMovieListModelEntity())
            }
            movieDataSourceLocal.getMovies()
        } catch (e: Exception) {
            // Si la llamada a la red falla, intenta obtener los datos de la base de datos local
            movieDataSourceLocal.getMovies()
        }
    }
}