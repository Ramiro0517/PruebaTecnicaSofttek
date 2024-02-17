package com.example.pruebatecnica.ui.movieList.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebatecnica.core.Constant
import com.example.pruebatecnica.databinding.ItemMovieBinding
import com.example.pruebatecnica.model.MovieListModel

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemMovieBinding.bind(itemView)
    private val context: Context = itemView.context

    fun render(movieListModel: MovieListModel,
               onClickListener: (MovieListModel) -> Unit) {


        Glide.with(context)
            .load("${Constant.BASE_URL_IMAGE}${movieListModel.posterPath}")
            // .apply(RequestOptions().override(Constant.IMAGE_ANCHO, Constant.IMAGE_ALTO))
            .into(binding.imageMoviePoster)
        binding.textMovieTitle.text = movieListModel.title

    }
}