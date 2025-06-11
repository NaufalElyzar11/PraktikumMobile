package com.example.modul4compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.modul4compose.data.Movie
import com.example.modul4compose.data.RetrofitInstance
import retrofit2.Response
import timber.log.Timber

class MoviesViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _movieDetail = MutableStateFlow<Movie?>(null)
    val movieDetail: StateFlow<Movie?> = _movieDetail

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies("a9bba3f6f1cd96583dbe3855dd165da1")
                if (response.isSuccessful) {
                    _movies.value = response.body()?.results ?: emptyList()
                } else {
                    Timber.e("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Timber.e("Error: ${e.message}")
            }
        }
    }

    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getMovieDetail(movieId, "a9bba3f6f1cd96583dbe3855dd165da1")
                if (response.isSuccessful) {
                    _movieDetail.value = response.body()
                } else {
                    Timber.e("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Timber.e("Error: ${e.message}")
            }
        }
    }
}
