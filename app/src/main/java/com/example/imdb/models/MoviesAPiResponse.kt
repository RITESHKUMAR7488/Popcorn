package com.example.imdb.models

import com.example.imdb.uis.fragments.Movies

data class MoviesAPiResponse(
    val movies: List<MovieList>,
    val page: Int
)