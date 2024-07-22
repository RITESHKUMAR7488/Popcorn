package com.example.imdb.uis

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.imdb.MyApplication
import com.example.imdb.R
import com.example.imdb.adapters.ViewPagerAdapter
import com.example.imdb.apiInterface.Api
import com.example.imdb.databinding.ActivityMoviesDescriptionBinding
import com.example.imdb.models.MovieApiResponse
import com.example.imdb.models.MovieList
import com.example.imdb.repository.Repository
import com.example.imdb.uis.fragments.Description
import com.example.imdb.uis.fragments.Shows
import com.example.imdb.uis.fragments.Simmilar
import com.example.imdb.viewModelFactory.MyViewModelFactory
import com.example.imdb.viewModels.MyViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MoviesDescription : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    private lateinit var binding: ActivityMoviesDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_description)
        with(binding) {


            val application = application as MyApplication
            val retrofitBuilder = application.retrofit
            val apiInterface = retrofitBuilder.create(Api::class.java)
            val repository = Repository(apiInterface)
            viewModel = ViewModelProvider(
                this@MoviesDescription,
                MyViewModelFactory(repository)
            )[MyViewModel::class.java]

            // for observing data
            val movieId = intent.getIntExtra("ids", 0)
            val isShow = intent.getBooleanExtra("isShow", false)

            if (isShow) {
                viewModel.getShow(movieId).observe(this@MoviesDescription) {
                    Log.d("moviesssss", "${it}")
                    setUi(it.show)
                    setUpViewPager(it, true)
                    viewModel.movieData = MovieList()
                    viewModel.movieData = it.movie
                }
            } else {
                viewModel.getMovie(movieId).observe(this@MoviesDescription) {
                    Log.d("moviesssss", "${it}")
                    setUi(it.movie)
                    setUpViewPager(it, false)
                    viewModel.movieData = MovieList()
                    viewModel.movieData = it.movie
                }
            }


        }

    }

    private fun setUpViewPager(movieApiResponse: MovieApiResponse, isShows: Boolean) {
        val adapter = ViewPagerAdapter(this@MoviesDescription)

        // add fragment to the list
        if (isShows) {
            adapter.addFragment(Description(movieApiResponse.show), "Description")
            //adapter.addFragment(Simmilar(movieApiResponse.similarMovies), "Similar")
        } else {
            adapter.addFragment(Description(movieApiResponse.movie), "Description")
            adapter.addFragment(Simmilar(movieApiResponse.similarMovies), "Similar")
        }


        // Adding the Adapter to the ViewPager2
        binding.pager.adapter = adapter
        binding.pager.isUserInputEnabled = false

        // bind the viewPager with the TabLayout using TabLayoutMediator
        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }

    private fun setUi(movie: MovieList) {
        with(binding) {
            Glide.with(this@MoviesDescription).load(movie.backdrop_path).into(backDropImage)
            Glide.with(this@MoviesDescription).load(movie.poster_path).into(posterPic)
            movieTitle.text = movie.original_title
            txReleasedate.text = movie.release_date
            txGenre.text = movie.genres?.get(0) ?: ""
            txDuration.text = movie.vote_average.toString()

        }

    }
}