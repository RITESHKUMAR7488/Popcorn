package com.example.imdb.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.models.HomeApiResponse
import com.example.imdb.models.MovieApiResponse
import com.example.imdb.models.MovieList
import com.example.imdb.models.MoviesAPiResponse
import com.example.imdb.models.SearchApiResponse
import com.example.imdb.models.ShowsApiResponse
import com.example.imdb.repository.Repository
import com.example.imdb.uis.fragments.Movies

// data store in form of live data which can be furthure absord in any activity or fragment
class MyViewModel(private val repository: Repository) : ViewModel() {
     var movieData : MovieList?=null
    fun getHome(): MutableLiveData<HomeApiResponse> {
        val data = MutableLiveData<HomeApiResponse>()
        repository.getHome(data)
        return data
    }

    fun getMovie(movieIds: Int): MutableLiveData<MovieApiResponse> {
        val data = MutableLiveData<MovieApiResponse>()
        repository.getMovie(data, movieIds)
        return data
    }
    fun getShow(movieIds: Int): MutableLiveData<MovieApiResponse> {
        val data = MutableLiveData<MovieApiResponse>()
        repository.getShow(data, movieIds)
        return data
    }
    fun getMovies(): MutableLiveData<MoviesAPiResponse> {
        val data = MutableLiveData<MoviesAPiResponse>()
        repository.getMovies(data)
        return data
    }

    fun getShows(): MutableLiveData<ShowsApiResponse>{
        val data = MutableLiveData<ShowsApiResponse>()
        repository.getShows(data)
        return data
    }

    fun search(query: String): MutableLiveData<SearchApiResponse> {
        val data = MutableLiveData<SearchApiResponse>()
        repository.search( data,query)
        return data
    }


}

