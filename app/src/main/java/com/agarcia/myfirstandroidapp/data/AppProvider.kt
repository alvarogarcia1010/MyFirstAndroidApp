package com.agarcia.myfirstandroidapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.agarcia.myfirstandroidapp.data.database.AppDatabase
import com.agarcia.myfirstandroidapp.data.remote.RetrofitInstance
import com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie.FavoriteMovieRepository
import com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie.FavoriteMovieRepositoryImpl
import com.agarcia.myfirstandroidapp.data.repository.Movie.MovieRepository
import com.agarcia.myfirstandroidapp.data.repository.Movie.MovieRepositoryImpl
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepository
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepositoryImpl

private const val USER_PREFERENCE_NAME = "user_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)

class AppProvider(context: Context) {
  private val appDatabase = AppDatabase.getDatabase(context)
  private val favoriteMovieDao = appDatabase.favoriteMovieDao()
  private val favoriteMovieRepository: FavoriteMovieRepository = FavoriteMovieRepositoryImpl(favoriteMovieDao)

  private val userPreferenceRepository: UserPreferencesRepository = UserPreferencesRepositoryImpl(context.dataStore)

  private val movieService = RetrofitInstance.movieService
  private val movieDao = appDatabase.movieDao()
  private val movieRepository: MovieRepository = MovieRepositoryImpl(movieDao, movieService)

  fun provideFavoriteMovieRepository() : FavoriteMovieRepository {
    return favoriteMovieRepository
  }

  fun provideUserPreferenceRepository() : UserPreferencesRepository {
    return userPreferenceRepository
  }

  fun providerMovieRepository() : MovieRepository{
    return movieRepository
  }
}