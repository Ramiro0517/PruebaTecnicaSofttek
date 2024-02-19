package com.example.pruebatecnica.ui.movieList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnica.R
import com.example.pruebatecnica.core.Resource
import com.example.pruebatecnica.data.local.AppDataBase
import com.example.pruebatecnica.data.local.LocalMovieDataSource
import com.example.pruebatecnica.databinding.FragmentMovieListBinding
import com.example.pruebatecnica.data.model.MovieListModel
import com.example.pruebatecnica.data.remote.MovieDataSource
import com.example.pruebatecnica.network.RetrofitClient
import com.example.pruebatecnica.repository.MovieListRepositoryImpl
import com.example.pruebatecnica.ui.movieList.adapter.MovieListAdapter
import com.example.pruebatecnica.viewmodels.MovieListViewModel

class MovieListFragment : Fragment() {

    private val movieListViewModel by viewModels<MovieListViewModel> {MovieListViewModel.MovieListViewModelFactory(MovieListRepositoryImpl(
        MovieDataSource(com.example.pruebatecnica.repository.RetrofitClient.webService),
        LocalMovieDataSource(AppDataBase.getDataBase(requireContext()).movieDao())
    )) }
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
       // movieListViewModel.fetchMovies()

        // movieListViewModel.onMovieClick()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun observer() {

        movieListViewModel.fetchMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapterMovie.listMovie = result.data.results
                    adapterMovie.notifyDataSetChanged()
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Error: ${result.exception}", Toast.LENGTH_LONG).show()
                    Log.e("Error1", "${result.exception}")
                }
            }
        })
      /*  movieListViewModel.listaPeliculas.observe(viewLifecycleOwner) {
            adapterMovie.listMovie = it
            adapterMovie.notifyDataSetChanged()
        }
        movieListViewModel.progress.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }*/
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