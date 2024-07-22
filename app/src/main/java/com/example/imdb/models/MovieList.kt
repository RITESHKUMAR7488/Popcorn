package com.example.imdb.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieList(
    val _id: Int?=null,
    val backdrop_path: String?=null,
    val contentType: String?=null,
    val first_aired: String?=null,
    val genres: List<String>?=null,
    val youtube_trailer: String?=null,
    val original_title: String?=null,
    val release_date: String?=null,
    val vote_average: Double?=null,
    val overview: String?=null,
    val poster_path: String?=null,
    val title: String?=null
):Serializable