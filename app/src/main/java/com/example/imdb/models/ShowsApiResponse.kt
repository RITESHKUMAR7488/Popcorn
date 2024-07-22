package com.example.imdb.models

data class ShowsApiResponse(
    val movies: List<MovieList>,
    val page: Int
)