package com.agarcia.myfirstandroidapp.ui.screens.MovieDetail

import androidx.lifecycle.ViewModel
import com.agarcia.myfirstandroidapp.data.dummy.dummyMovies
import com.agarcia.myfirstandroidapp.data.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow

class MovieDetailViewModel: ViewModel() {
  private val _movies = MutableStateFlow<List<Movie>>(emptyList())

  init {
    loadMovies()
  }

  private fun loadMovies() {
    _movies.value = dummyMovies
  }

  fun getMovieById(movieId: Int): Movie? {
    return _movies.value.firstOrNull { it.id == movieId }
  }

}