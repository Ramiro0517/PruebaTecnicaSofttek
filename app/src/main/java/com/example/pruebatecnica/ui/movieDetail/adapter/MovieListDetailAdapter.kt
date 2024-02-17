package com.example.pruebatecnica.ui.movieDetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pruebatecnica.R
import com.example.pruebatecnica.core.Constant
import com.example.pruebatecnica.model.MovieListModel

class MovieListDetailAdapter(val context : Context, var listMovie : List<MovieListModel>) : RecyclerView.Adapter<MovieListDetailAdapter.MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = listMovie[position]
        Glide.with(context)
            .load("${Constant.BASE_URL_IMAGE}${movie.posterPath}")
            .apply(RequestOptions().override(Constant.IMAGE_ANCHO, Constant.IMAGE_ALTO))
            .into(holder.poster)
        holder.title.text = movie.title
        holder.textMovieRating.text = movie.voteAverage.toString()
        holder.releaseDate.text = movie.releaseDate
    }

    override fun getItemCount(): Int = listMovie.size

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.textMovieTitleDetail)
        val textMovieRating = itemView.findViewById<TextView>(R.id.textMovieRatingDetail)
        val releaseDate = itemView.findViewById<TextView>(R.id.textMovieReleaseDateDetail)
        val poster = itemView.findViewById<ImageView>(R.id.imageMoviePosterDetail)
    }

}