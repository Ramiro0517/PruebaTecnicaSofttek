package com.example.pruebatecnica.network

import com.example.pruebatecnica.network.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ):Response<MovieListResponse>

}