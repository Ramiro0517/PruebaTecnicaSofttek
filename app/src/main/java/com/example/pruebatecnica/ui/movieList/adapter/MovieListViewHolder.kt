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
import com.example.pruebatecnica.data.model.MovieListModel

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemMovieBinding.bind(itemView)
    fun render(movieListModel: MovieListModel,
               onClickListener: (MovieListModel) -> Unit) {

        Glide.with(binding.imageMoviePoster.context)
            .load("${Constant.BASE_URL_IMAGE}${movieListModel.posterPath}")
            // .apply(RequestOptions().override(Constant.IMAGE_ANCHO, Constant.IMAGE_ALTO))
            .into(binding.imageMoviePoster)
        binding.textMovieTitle.text = movieListModel.title

        itemView.setOnClickListener {
            onClickListener(movieListModel)
        }

    }
}