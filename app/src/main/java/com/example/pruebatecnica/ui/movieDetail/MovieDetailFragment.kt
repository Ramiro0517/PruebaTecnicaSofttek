package com.example.pruebatecnica.ui.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pruebatecnica.R
import com.example.pruebatecnica.core.Constant
import com.example.pruebatecnica.databinding.FragmentMovieDetailBinding
import com.example.pruebatecnica.model.MovieListModel


class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieListModel = arguments?.getSerializable("movieModel") as? MovieListModel
        if (movieListModel != null) {
            Glide.with(binding.imageMoviePosterDetail.context)
                .load("${Constant.BASE_URL_IMAGE}${movieListModel.posterPath}")
                .apply(RequestOptions().override(Constant.IMAGE_ANCHO, Constant.IMAGE_ALTO))
                .into(binding.imageMoviePosterDetail)
            binding.textMovieTitleDetail.text = "Tittle: ${movieListModel.title}"
            binding.textMovieOverviewDetail.text = "Resumen de la película: ${movieListModel.overview}"
            binding.textMovieRatingDetail.text = "Nota de la película: ${movieListModel.voteAverage}"
            binding.textMovieReleaseDateDetail.text = "Fecha de lanzamiento: ${movieListModel.releaseDate}"


        }

    }

}