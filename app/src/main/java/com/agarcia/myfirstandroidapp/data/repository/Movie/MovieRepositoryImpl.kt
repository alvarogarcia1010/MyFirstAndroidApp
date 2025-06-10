package com.agarcia.myfirstandroidapp.data.repository.Movie

import com.agarcia.myfirstandroidapp.data.database.dao.MovieDao
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.remote.Movie.MovieService
import com.agarcia.myfirstandroidapp.data.remote.Movie.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import android.util.Log
import com.agarcia.myfirstandroidapp.data.database.entities.toDomain
import com.agarcia.myfirstandroidapp.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
  private val movieDao: MovieDao,
  private val movieService: MovieService
):MovieRepository {

  override fun getMovies (): Flow<Resource<List<Movie>>> = flow {
   emit(Resource.Loading)
    try {
      val remoteMovies = movieService.getPopularMovies().result

      if (remoteMovies.isNotEmpty()) {
        movieDao.insertMovies(remoteMovies.map { it.toEntity() })
      }
    }
    catch (e: Exception) {
      Log.e("MovieRepository", "Error fetching movies from remote: ${e.message}")
    }

    val localMoviesFlow =  movieDao.getMovies()
        .map { entities ->
          val movies = entities.map { it.toDomain() }

          when {
            movies.isNotEmpty() -> Resource.Success(movies)
            else -> Resource.Error("No movies found")
          }
        }.distinctUntilChanged()


    emitAll(localMoviesFlow)
    }.flowOn(Dispatchers.IO)
}