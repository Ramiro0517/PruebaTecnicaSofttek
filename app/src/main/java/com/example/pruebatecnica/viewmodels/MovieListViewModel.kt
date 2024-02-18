package com.example.pruebatecnica.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.core.Constant
import com.example.pruebatecnica.model.MovieListModel
import com.example.pruebatecnica.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel: ViewModel() {

    private var _listaPeliculas = MutableLiveData<List<MovieListModel>>()
    val listaPeliculas : LiveData<List<MovieListModel>> = _listaPeliculas

    private var _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean> = _progress

    fun getMovie(){
        viewModelScope.launch(Dispatchers.IO) {
            _progress.postValue(true)
            val response = RetrofitClient.apiService.getUpcomingMovies(1,Constant.API_KEY)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    _listaPeliculas.value = response.body()?.results?.sortedByDescending { it.voteAverage }
                }
                else{
                    _listaPeliculas.value = emptyList()
                }
            }
            _progress.postValue(false)

        }
    }
}