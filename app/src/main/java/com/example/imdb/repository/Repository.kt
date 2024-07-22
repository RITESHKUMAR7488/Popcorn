package com.example.imdb.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.imdb.apiInterface.Api
import com.example.imdb.models.HomeApiResponse
import com.example.imdb.models.MovieApiResponse
import com.example.imdb.models.MoviesAPiResponse
import com.example.imdb.models.SearchApiResponse
import com.example.imdb.models.ShowsApiResponse
import com.example.imdb.uis.fragments.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
// to call network request

class Repository(private val api: Api) {
    fun getHome(data: MutableLiveData<HomeApiResponse>) {
        api.getHome().enqueue(object : Callback<HomeApiResponse?> {
            override fun onResponse(p0: Call<HomeApiResponse?>, p1: Response<HomeApiResponse?>) {
                data.value = p1.body()
            }

            override fun onFailure(p0: Call<HomeApiResponse?>, p1: Throwable) {
                Log.d("Error", p1.message.toString())
            }
        })
    }

    fun getMovie(data: MutableLiveData<MovieApiResponse>, movieId: Int) {
        api.getMovie(movieId).enqueue(object : Callback<MovieApiResponse?> {
            override fun onResponse(p0: Call<MovieApiResponse?>, p1: Response<MovieApiResponse?>) {
                data.value = p1.body()
            }

            override fun onFailure(p0: Call<MovieApiResponse?>, p1: Throwable) {
                Log.d("Error", p1.message.toString())
            }

        })
    }
    fun getShow(data: MutableLiveData<MovieApiResponse>, movieId: Int) {
        api.getShow(movieId).enqueue(object : Callback<MovieApiResponse?> {
            override fun onResponse(p0: Call<MovieApiResponse?>, p1: Response<MovieApiResponse?>) {
                data.value = p1.body()
            }

            override fun onFailure(p0: Call<MovieApiResponse?>, p1: Throwable) {
                Log.d("Error", p1.message.toString())
            }

        })
    }

    fun getMovies(data: MutableLiveData<MoviesAPiResponse>) {
        api.getMovies().enqueue(object : Callback<MoviesAPiResponse?> {
            override fun onResponse(
                p0: Call<MoviesAPiResponse?>,
                p1: Response<MoviesAPiResponse?>
            ) {
                data.value = p1.body()
            }

            override fun onFailure(p0: Call<MoviesAPiResponse?>, p1: Throwable) {
                Log.d("Error", p1.message.toString())
            }

        })
    }

    fun getShows(data: MutableLiveData<ShowsApiResponse>) {
        api.getShows().enqueue(object : Callback<ShowsApiResponse?> {


            override fun onResponse(
                p0: Call<ShowsApiResponse?>,
                p1: Response<ShowsApiResponse?>
            ) {
               data.value = p1.body()
            }

            override fun onFailure(p0: Call<ShowsApiResponse?>, p1: Throwable) {
                Log.d("Error", p1.message.toString())
            }


        })

    }

    fun search(data: MutableLiveData<SearchApiResponse>, query: String){
        api.search(query).enqueue(object : Callback<SearchApiResponse?> {
            override fun onResponse(
                p0: Call<SearchApiResponse?>,
                p1: Response<SearchApiResponse?>
            ) {
                data.value=p1.body()
            }

            override fun onFailure(p0: Call<SearchApiResponse?>, p1: Throwable) {
                Log.d("Error", p1.message.toString())
            }
        })
    }
}