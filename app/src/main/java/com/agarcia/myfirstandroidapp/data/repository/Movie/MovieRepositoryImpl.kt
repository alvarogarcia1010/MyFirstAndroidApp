package com.agarcia.myfirstandroidapp.data.repository.Movie

import com.agarcia.myfirstandroidapp.data.dummy.dummyMovies
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.remote.Movie.MovieService
import com.agarcia.myfirstandroidapp.data.remote.Responses.toDomain
import kotlinx.coroutines.delay

class MovieRepositoryImpl(
  private val movieService: MovieService
):MovieRepository {

  override suspend fun getMovies(): List<Movie> {
    return movieService.getPopularMovies("Bearer MyToken").results.map{ it.toDomain()}
  }
}