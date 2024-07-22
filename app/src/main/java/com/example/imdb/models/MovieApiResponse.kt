package com.example.imdb.models

import retrofit2.http.Query

data class MovieApiResponse(
    val movie: MovieList,
    val similarMovies: List<MovieList>,
    val show: MovieList,
    val query: String,
    val contents: List<MovieList>


)