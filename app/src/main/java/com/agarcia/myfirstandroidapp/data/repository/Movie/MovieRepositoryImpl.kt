package com.agarcia.myfirstandroidapp.data.repository.Movie

import android.util.Log
import com.agarcia.myfirstandroidapp.data.database.dao.MovieDao
import com.agarcia.myfirstandroidapp.data.database.entities.toDomain
import com.agarcia.myfirstandroidapp.data.dummy.dummyMovies
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.remote.Movie.MovieService
import com.agarcia.myfirstandroidapp.data.remote.Responses.toDomain
import com.agarcia.myfirstandroidapp.data.remote.Responses.toEntity
import com.agarcia.myfirstandroidapp.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
  private val movieService: MovieService,
  private val movieDao: MovieDao
):MovieRepository {

  override suspend fun getMovies(): Flow<Resource<List<Movie>>> = flow {
    emit(Resource.Loading)

    try {
      val remoteMovies = movieService.getPopularMovies().results

      if (remoteMovies.isNotEmpty()) {
        movieDao.insertMovies(remoteMovies.map { it.toEntity() })
      }
    }
    catch (e: Exception) {
      Log.d("MovieRepositoryImpl", "Error fetching movies: ${e.message}")
    }

    val localMovies = movieDao.getMovies().map{ entities ->
      val movies = entities.map { it.toDomain() }

      if (movies.isEmpty()) {
        Resource.Error("No movies found")
      } else {
        Resource.Success(movies)
      }
    }.distinctUntilChanged()

    emitAll(localMovies)
  }.flowOn(Dispatchers.IO)
}