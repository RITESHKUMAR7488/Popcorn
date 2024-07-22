package com.example.imdb.apiInterface

import com.example.imdb.models.HomeApiResponse
import com.example.imdb.models.MovieApiResponse
import com.example.imdb.models.MoviesAPiResponse
import com.example.imdb.models.SearchApiResponse
import com.example.imdb.models.ShowsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Api konse type ka hai

interface Api {
    @GET("home")
    fun getHome(): Call<HomeApiResponse>

    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") movieId: Int): Call<MovieApiResponse>

    @GET("show/{movie_id}")
    fun getShow(@Path("movie_id") movieId: Int): Call<MovieApiResponse>

    @GET("movies")
    fun getMovies(): Call<MoviesAPiResponse>

    @GET("shows")
    fun  getShows(): Call<ShowsApiResponse>

    @GET("search")
    fun search(@Query("query") query: String): Call<SearchApiResponse>


}