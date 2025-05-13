package com.agarcia.myfirstandroidapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.agarcia.myfirstandroidapp.data.database.AppDatabase
import com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie.FavoriteMovieRepository
import com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie.FavoriteMovieRepositoryImpl
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepository
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepositoryImpl

private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
  name = LAYOUT_PREFERENCE_NAME
)

class AppContainer(context: Context) {
  private val appDatabase = AppDatabase.getDatabase(context)
  private val favoriteMovieDao = appDatabase.favoriteMovieDao()
  private val favoriteMovieRepository = FavoriteMovieRepositoryImpl(favoriteMovieDao)

  private val userPreferencesRepository = UserPreferencesRepositoryImpl(context.dataStore)

  fun provideFavoriteMovieRepository() : FavoriteMovieRepository {
    return favoriteMovieRepository
  }

  fun provideUserPreferencesRepository() : UserPreferencesRepository {
    return userPreferencesRepository
  }
}