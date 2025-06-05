package com.agarcia.myfirstandroidapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agarcia.myfirstandroidapp.data.database.dao.FavoriteMovieDao
import com.agarcia.myfirstandroidapp.data.database.dao.MovieDao
import com.agarcia.myfirstandroidapp.data.database.entities.FavoriteMovieEntity
import com.agarcia.myfirstandroidapp.data.database.entities.MovieEntity

@Database(
  entities = [MovieEntity::class, FavoriteMovieEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun favoriteMovieDao(): FavoriteMovieDao

  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context = context.applicationContext,
          klass = AppDatabase::class.java,
          name = "movies_database"
        )
          .fallbackToDestructiveMigration(false)
          .build()
          .also {
            INSTANCE = it
          }
        instance
      }
    }
  }
}