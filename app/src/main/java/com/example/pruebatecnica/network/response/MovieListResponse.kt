package com.example.pruebatecnica.network.response

import com.example.pruebatecnica.data.model.MovieListModel
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results")
    val results: List<MovieListModel>
)