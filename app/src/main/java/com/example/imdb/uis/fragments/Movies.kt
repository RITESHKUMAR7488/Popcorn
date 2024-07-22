package com.example.imdb.uis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.imdb.MyApplication
import com.example.imdb.R
import com.example.imdb.adapters.HomeItemAdapter
import com.example.imdb.apiInterface.Api
import com.example.imdb.databinding.FragmentMoviesBinding
import com.example.imdb.models.MoviesAPiResponse
import com.example.imdb.repository.Repository
import com.example.imdb.viewModelFactory.MyViewModelFactory
import com.example.imdb.viewModels.MyViewModel


class Movies() : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MyViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val application = requireActivity().application as MyApplication
            val retrofitBuilder = application.retrofit
            val apiInterface = retrofitBuilder.create(Api::class.java)
            val repository = Repository(apiInterface)

            viewModel = ViewModelProvider(
                requireActivity(),
                MyViewModelFactory(repository)
            )[MyViewModel::class.java]


            viewModel.getMovies().observe(viewLifecycleOwner) {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility=View.GONE
                binding.mainLayout.visibility=View.VISIBLE
                val adapter = HomeItemAdapter(it.movies, requireActivity())
                rvParent.adapter = adapter

            }
        }


    }
}