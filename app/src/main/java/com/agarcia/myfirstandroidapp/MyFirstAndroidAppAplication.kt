package com.agarcia.myfirstandroidapp

import android.app.Application
import com.agarcia.myfirstandroidapp.data.AppContainer

class MyFirstAndroidAppAplication: Application() {
  val appContainer by lazy {
    AppContainer(context = this)
  }

  override fun onCreate() {
    super.onCreate()
    val favoriteMovieRepository = appContainer.provideFavoriteMovieRepository()
    val userPreferencesRepository = appContainer.provideUserPreferencesRepository()
  }
}