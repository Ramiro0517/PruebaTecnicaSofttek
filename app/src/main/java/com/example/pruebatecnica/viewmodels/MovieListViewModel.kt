package com.example.pruebatecnica.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.pruebatecnica.core.Constant
import com.example.pruebatecnica.core.Resource
import com.example.pruebatecnica.data.local.LocalMovieDataSource
import com.example.pruebatecnica.data.model.MovieListModel
import com.example.pruebatecnica.data.model.MovieListModelEntity
import com.example.pruebatecnica.network.RetrofitClient
import com.example.pruebatecnica.repository.MovieListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val repository: MovieListRepository): ViewModel() {
    //private val localMovieDataSource: LocalMovieDataSource
    private var _listaPeliculas = MutableLiveData<List<MovieListModel>>()
    val listaPeliculas : LiveData<List<MovieListModel>> = _listaPeliculas

    private var _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean> = _progress

    /*fun getMovie(){
        viewModelScope.launch(Dispatchers.IO) {
            _progress.postValue(true)
            val response = RetrofitClient.apiService.getUpcomingMovies(1,Constant.API_KEY)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    _listaPeliculas.value = response.body()?.results?.sortedByDescending { it.voteAverage }
                    repository.getMovie()
                    /* Save movies to local database
                    response.body()?.results?.forEach { movie ->
                        localMovieDataSource.saveMovies(
                            MovieListModelEntity(
                            id = movie.id,
                            originalTitle = movie.originalTitle,
                            posterPath = movie.posterPath,
                            title = movie.title,
                            voteAverage = movie.voteAverage,
                            releaseDate = movie.releaseDate,
                            overview = movie.overview
                        )
                        )
                    }*/
                }
                else{
                    //_listaPeliculas.value = emptyList()
                    _listaPeliculas.value = repository.getMovie().results.sortedByDescending { it.voteAverage }
                }
            }
            _progress.postValue(false)

        }
    }*/

    fun fetchMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getMovie()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    class MovieListViewModelFactory(private val repository: MovieListRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(MovieListRepository::class.java).newInstance(repository)
        }
    }
}