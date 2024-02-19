package com.example.pruebatecnica.repository

import com.example.pruebatecnica.core.Constant
import com.example.pruebatecnica.network.response.MovieListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): MovieListResponse
}
object RetrofitClient {

    val webService  by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WebService::class.java)
    }
}