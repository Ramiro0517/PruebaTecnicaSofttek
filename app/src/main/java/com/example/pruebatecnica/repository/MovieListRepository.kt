package com.example.pruebatecnica.repository

import com.example.pruebatecnica.network.response.MovieListResponse

interface MovieListRepository {

    suspend fun getMovie():MovieListResponse
}