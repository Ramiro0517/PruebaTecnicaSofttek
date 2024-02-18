package com.example.pruebatecnica.ui.movieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.FragmentMovieListBinding
import com.example.pruebatecnica.model.MovieListModel
import com.example.pruebatecnica.ui.movieList.adapter.MovieListAdapter
import com.example.pruebatecnica.viewmodels.MovieListViewModel

class MovieListFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by viewModels()
    private var _binding: FragmentMovieListBinding? = null
    private lateinit var adapterMovie: MovieListAdapter
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        initRecyclerView()
        observer()
        movieListViewModel.getMovie()

        // movieListViewModel.onMovieClick()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun observer() {
        movieListViewModel.listaPeliculas.observe(viewLifecycleOwner) {
            adapterMovie.listMovie = it
            adapterMovie.notifyDataSetChanged()
        }
        movieListViewModel.progress.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerView() {

        val layoutManager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.recyclerViewMovies.layoutManager = layoutManager
        adapterMovie = MovieListAdapter(arrayListOf()) { onItemSelected(it) }
        binding.recyclerViewMovies.adapter = adapterMovie
        binding.recyclerViewMovies.addItemDecoration(decoration)

    }

    private fun onItemSelected(movieListModel: MovieListModel) {
        val bundle = Bundle().apply {
            putSerializable("movieModel", movieListModel)
        }
        navController.navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)

    }


}