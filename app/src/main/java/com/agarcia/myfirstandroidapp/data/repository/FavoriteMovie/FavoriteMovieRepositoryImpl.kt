package com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie

import com.agarcia.myfirstandroidapp.data.database.dao.FavoriteMovieDao
import com.agarcia.myfirstandroidapp.data.database.entities.toDomain
import com.agarcia.myfirstandroidapp.data.model.FavoriteMovie
import com.agarcia.myfirstandroidapp.data.model.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl(
  private val favoriteMovieDao: FavoriteMovieDao,
) : FavoriteMovieRepository {

    override fun getFavoriteMovies(): Flow<List<FavoriteMovie>> {
        val response = favoriteMovieDao.getFavoriteMovies()
        return response.map { list -> list.map { it.toDomain()} }
    }

    override suspend fun addFavoriteMovie(favoriteMovieEntity: FavoriteMovie) {
        favoriteMovieDao.addMovieToFavorites(favoriteMovieEntity.toDatabase())
    }

    override suspend fun removeFavoriteMovie(favoriteMovieEntity: FavoriteMovie) {
        favoriteMovieDao.deleteMovieFromFavorites(favoriteMovieEntity.toDatabase())
    }

    override fun isFavorite(movieId: Int) : Flow<Boolean> {
      return favoriteMovieDao.getFavoriteMovieById(movieId).map { it != null }
    }
}