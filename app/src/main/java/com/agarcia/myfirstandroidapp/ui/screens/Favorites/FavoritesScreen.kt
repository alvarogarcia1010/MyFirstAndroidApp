package com.agarcia.myfirstandroidapp.ui.screens.Favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agarcia.myfirstandroidapp.ui.components.MoviePoster
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.agarcia.myfirstandroidapp.data.model.toMovie


@Composable
fun Favorites(
  favoritesViewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModel.Factory)
) {
  val favoriteMovies by favoritesViewModel.favoriteMovies.collectAsStateWithLifecycle(emptyList())

  LazyVerticalGrid(
    columns = GridCells.Fixed(3),
    contentPadding = PaddingValues(0.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    modifier = Modifier.fillMaxSize().padding(16.dp)
  ) {
    items(favoriteMovies) { movie ->
      MoviePoster(
        movie = movie.toMovie(),
        isFavorite = true,
        onMovieClick = {},
        onFavoriteClick = { favoritesViewModel.toggleFavorite(movie) }

      )
    }
  }
}