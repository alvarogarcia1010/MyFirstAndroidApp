package com.agarcia.myfirstandroidapp.ui.screens.MovieList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agarcia.myfirstandroidapp.data.dummy.dummyMovies
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.repository.MovieRepository
import com.agarcia.myfirstandroidapp.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieListViewModel: ViewModel() {
  val movieRepository: MovieRepository = MovieRepositoryImpl()

  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies: StateFlow<List<Movie>> = _movies

  private val _loading = MutableStateFlow<Boolean>(false)
  val loading: StateFlow<Boolean> = _loading

  fun loadMovies() {
    _loading.value = true
    viewModelScope.launch {
      _movies.value = movieRepository.getMovies()
      _loading.value = false
    }
  }

  fun loadMovies2() {
    _movies.value = dummyMovies
  }
}