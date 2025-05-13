package com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie

import com.agarcia.myfirstandroidapp.data.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getFavoriteMovies(): Flow<List<FavoriteMovie>>
    suspend fun addFavoriteMovie(favoriteMovieEntity: FavoriteMovie)
    suspend fun removeFavoriteMovie(favoriteMovieEntity: FavoriteMovie)
    fun isFavorite(movieId: Int): Flow<Boolean>
}