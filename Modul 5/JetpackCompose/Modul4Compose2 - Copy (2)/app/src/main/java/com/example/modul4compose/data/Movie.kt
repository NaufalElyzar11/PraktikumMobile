package com.example.modul4compose.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(val results: List<Movie>)

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String
)
