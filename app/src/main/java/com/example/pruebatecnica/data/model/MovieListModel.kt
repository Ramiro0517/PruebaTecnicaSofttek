package com.example.pruebatecnica.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pruebatecnica.network.response.MovieListResponse
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

) : Serializable


//Room
@Entity(tableName = "movie_list")
data class MovieListModelEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle: String,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String

)

fun List<MovieListModelEntity>.toMovieListModel(): MovieListResponse {
    val resultList = mutableListOf<MovieListModel>()
    this.forEach {
        resultList.add(it.toMovieListModel())
    }
    return MovieListResponse(resultList)
}

fun MovieListModelEntity.toMovieListModel(): MovieListModel = MovieListModel(
    this.id,
    this.originalTitle,
    this.posterPath,
    this.title,
    this.voteAverage,
    this.releaseDate,
    this.overview
)
