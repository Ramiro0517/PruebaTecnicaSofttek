package com.example.pruebatecnica.ui.movieList.adapter

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

class MovieListAdapter( var listMovie : List<MovieListModel>, private val onClickListener:(MovieListModel)->Unit) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.render(movie, onClickListener)

    }

    override fun getItemCount(): Int = listMovie.size



}