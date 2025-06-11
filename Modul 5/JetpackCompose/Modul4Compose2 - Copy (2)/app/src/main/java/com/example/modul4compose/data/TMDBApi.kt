package com.example.modul4compose.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDBApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @retrofit2.http.Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<Movie>
}