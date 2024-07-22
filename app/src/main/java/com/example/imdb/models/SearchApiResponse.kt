package com.example.imdb.models

data class SearchApiResponse(
    val contents: List<MovieList>,
    val query: String
)