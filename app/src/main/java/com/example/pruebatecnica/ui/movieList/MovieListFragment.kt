package com.example.pruebatecnica.ui.movieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnica.databinding.FragmentMovieListBinding
import com.example.pruebatecnica.ui.movieList.adapter.MovieListAdapter
import com.example.pruebatecnica.viewmodels.MovieListViewModel

class MovieListFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by viewModels()
    private var _binding: FragmentMovieListBinding? = null
    private lateinit var adapterMovie:MovieListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        initReciclerView()
        observer()
        movieListViewModel.getMovie()
       // movieListViewModel.onMovieClick()
        return binding.root
    }

    private fun observer() {
        movieListViewModel.listaPeliculas.observe(viewLifecycleOwner) {
            adapterMovie.listMovie = it
            adapterMovie.notifyDataSetChanged()
        }
    }

    private fun initReciclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewMovies.layoutManager = layoutManager
        adapterMovie = MovieListAdapter(requireContext(), arrayListOf(), movieListViewModel::onMovieClick)
        binding.recyclerViewMovies.adapter = adapterMovie

    }

}