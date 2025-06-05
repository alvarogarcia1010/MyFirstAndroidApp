package com.agarcia.myfirstandroidapp.data.repository.Movie

import com.agarcia.myfirstandroidapp.data.database.dao.MovieDao
import com.agarcia.myfirstandroidapp.data.database.entities.toDomain
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.remote.Movie.MovieService
import com.agarcia.myfirstandroidapp.data.remote.Movie.toDomain
import com.agarcia.myfirstandroidapp.data.remote.Movie.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
  private val movieDao: MovieDao,
  private val movieService: MovieService
):MovieRepository {

  override suspend fun getMovies(): List<Movie> {
    try {
      var movies = movieService.getPopularMovies().result
      if (movies.isNotEmpty()) {
        movieDao.insertMovies(movies.map { it.toEntity() })
      }

      return movieDao.getMovies().first().map { it.toDomain() }
    }
    catch (e: Exception) {
      return movieDao.getMovies().first().map { it.toDomain() }
    }
  }
}