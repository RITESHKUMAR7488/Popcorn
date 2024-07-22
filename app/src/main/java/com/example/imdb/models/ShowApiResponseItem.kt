package com.example.imdb.models

data class ShowApiResponseItem(
    val movies: List<MovieList>,
    val title: String
)