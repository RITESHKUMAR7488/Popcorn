package com.example.imdb.models

data class HomeApiResponseItem(
    val movies: List<MovieList>,
    val title: String
)