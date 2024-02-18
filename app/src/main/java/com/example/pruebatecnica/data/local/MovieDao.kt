package com.example.pruebatecnica.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebatecnica.data.model.MovieListModelEntity


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_list")
    suspend fun getAllMovies(): List<MovieListModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movieList: MovieListModelEntity)
}