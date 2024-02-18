package com.example.pruebatecnica.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieListModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val overview: String

): Serializable
