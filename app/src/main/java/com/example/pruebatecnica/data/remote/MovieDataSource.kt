package com.example.pruebatecnica.data.remote

import com.example.pruebatecnica.core.Constant
import com.example.pruebatecnica.network.response.MovieListResponse
import com.example.pruebatecnica.repository.WebService

class MovieDataSource(private val webService: WebService) {

    suspend fun getMovies(): MovieListResponse = webService.getUpcomingMovies(1, Constant.API_KEY)
}