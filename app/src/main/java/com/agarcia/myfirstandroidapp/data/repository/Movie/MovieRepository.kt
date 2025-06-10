package com.agarcia.myfirstandroidapp.data.repository.Movie

import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
  suspend fun getMovies(): Flow<Resource<List<Movie>>>
}