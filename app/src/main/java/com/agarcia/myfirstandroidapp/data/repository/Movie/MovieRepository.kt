package com.agarcia.myfirstandroidapp.data.repository.Movie

import com.agarcia.myfirstandroidapp.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
  suspend fun getMovies(): List<Movie>
}