package com.agarcia.myfirstandroidapp.ui.screens.MovieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.agarcia.myfirstandroidapp.MyFirstAndroidAppAplication
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.model.toFavoriteMovie
import com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie.FavoriteMovieRepository
import com.agarcia.myfirstandroidapp.data.repository.Movie.MovieRepository
import com.agarcia.myfirstandroidapp.data.repository.Movie.MovieRepositoryImpl
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepository
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieListViewModel(
  private val userPreferencesRepository: UserPreferencesRepository,
  private val favoriteMovieRepository: FavoriteMovieRepository
): ViewModel() {
  val movieRepository: MovieRepository = MovieRepositoryImpl()

  private val _movies = MutableStateFlow<List<Movie>>(emptyList())
  val movies: StateFlow<List<Movie>> = _movies

  private val _loading = MutableStateFlow<Boolean>(false)
  val loading: StateFlow<Boolean> = _loading

  val isLinearLayout: StateFlow<Boolean> =
    userPreferencesRepository.isLinearLayout.map { it }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = true
    )

  fun loadMovies() {
    _loading.value = true
    viewModelScope.launch {
      _movies.value = movieRepository.getMovies()
      _loading.value = false
    }
  }

  fun changeLayoutPreferences(isLinearLayout: Boolean) {
    viewModelScope.launch {
      userPreferencesRepository.saveLayoutPreference(isLinearLayout)
    }
  }

  fun isFavorite(movieId: Int): StateFlow<Boolean> {
    return favoriteMovieRepository.isFavorite(movieId)
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
      )
  }

  fun toggleFavorite(movie: Movie) {
    viewModelScope.launch {
      val isFavorite = favoriteMovieRepository.isFavorite(movie.id).first()

      if (isFavorite) {
        favoriteMovieRepository.removeFavoriteMovie(movie.toFavoriteMovie())
      }
      else {
        favoriteMovieRepository.addFavoriteMovie(movie.toFavoriteMovie())
      }
    }
  }

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        val application = (this[APPLICATION_KEY] as MyFirstAndroidAppAplication)
        MovieListViewModel(
          application.appContainer.provideUserPreferencesRepository(),
          application.appContainer.provideFavoriteMovieRepository()
        )
      }
    }
  }
}