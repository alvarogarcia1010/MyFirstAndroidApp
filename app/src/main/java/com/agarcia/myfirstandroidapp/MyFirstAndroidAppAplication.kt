package com.agarcia.myfirstandroidapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepository

private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
  name = LAYOUT_PREFERENCE_NAME
)

class MyFirstAndroidAppAplication: Application() {
  lateinit var userPreferencesRepository: UserPreferencesRepository

  override fun onCreate() {
    super.onCreate()
    userPreferencesRepository = UserPreferencesRepository(dataStore)
  }
}