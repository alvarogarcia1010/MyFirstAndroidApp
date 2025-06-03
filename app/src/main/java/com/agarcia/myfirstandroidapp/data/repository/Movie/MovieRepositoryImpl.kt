package com.agarcia.myfirstandroidapp.data.repository.Movie

import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.remote.Movie.MovieService
import com.agarcia.myfirstandroidapp.data.remote.Movie.toDomain

class MovieRepositoryImpl(
  private val movieService: MovieService
):MovieRepository {

  override suspend fun getMovies(): List<Movie> {
    return movieService.getPopularMovies().result.map { it.toDomain() }
  }
}