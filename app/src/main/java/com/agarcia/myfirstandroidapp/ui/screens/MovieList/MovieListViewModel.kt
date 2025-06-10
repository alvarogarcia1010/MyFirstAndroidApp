package com.agarcia.myfirstandroidapp.ui.screens.MovieList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.agarcia.myfirstandroidapp.MyFirstAndroidAppAplication
import com.agarcia.myfirstandroidapp.data.dummy.dummyMovies
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.model.toFavoriteMovie
import com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie.FavoriteMovieRepository
import com.agarcia.myfirstandroidapp.data.repository.Movie.MovieRepository
import com.agarcia.myfirstandroidapp.data.repository.Movie.MovieRepositoryImpl
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepository
import com.agarcia.myfirstandroidapp.helpers.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieListViewModel(
  private val movieRepository: MovieRepository,
  private val userPreferenceRepository: UserPreferencesRepository,
  private val favoriteMovieRepository: FavoriteMovieRepository
): ViewModel() {

  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies: StateFlow<List<Movie>> = _movies

  private val _loading = MutableStateFlow<Boolean>(false)
  val loading : StateFlow<Boolean> = _loading

  private val _isRefreshing = MutableStateFlow<Boolean>(false)
  val isRefreshing : StateFlow<Boolean> = _isRefreshing

  val isLinearLayout : StateFlow<Boolean> = userPreferenceRepository.isLinearLayout.map { it }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = true
    )

  fun changeLayoutPreferences(isLinearLayout: Boolean) {
    viewModelScope.launch {
      userPreferenceRepository.saveLayoutPreference(isLinearLayout)
    }
  }

  fun loadMovies (isRefreshing: Boolean = false) {
    viewModelScope.launch {
      movieRepository.getMovies().collect({ resource ->
        when (resource) {
          is Resource.Loading -> {
            if (isRefreshing) {
              _isRefreshing.value = true
            } else {
              _loading.value = true
            }
          }
          is Resource.Success -> {
            _movies.value = resource.data
            _loading.value = false
            _isRefreshing.value = false
          }
          is Resource.Error -> {
            _loading.value = false
            _isRefreshing.value = false
          }
        }

      })
    }
  }

  fun isFavoriteMovie(movieId: Int): StateFlow<Boolean> {
    return favoriteMovieRepository.isFavorite(movieId)
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
      )
  }

  fun toggleFavoriteMovie(movie: Movie) {
    viewModelScope.launch {
      val isFavorite = favoriteMovieRepository.isFavorite(movie.id).first()
      
      if (isFavorite) {
        favoriteMovieRepository.removeMovieFromFavorites(movie.toFavoriteMovie())
      } else {
        favoriteMovieRepository.addMovieToFavorites(movie.toFavoriteMovie())
      }
    }
  }

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val aplication = this[APPLICATION_KEY] as MyFirstAndroidAppAplication
        MovieListViewModel(
          aplication.appProvider.provideMovieRepository(),
          aplication.appProvider.provideUserPreferenceRepository(),
          aplication.appProvider.provideFavoriteMovieRepository()
        )
      }
    }
  }

}