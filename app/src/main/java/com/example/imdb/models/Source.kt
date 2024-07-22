package com.example.imdb.models

data class Source(
    val display_name: String,
    val info: String,
    val link: String,
    val platform: Platform,
    val source: String,
    val type: String
)