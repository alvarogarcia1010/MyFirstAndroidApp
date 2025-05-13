package com.agarcia.myfirstandroidapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.agarcia.myfirstandroidapp.data.model.Movie

@Composable
fun MoviePoster(
  movie: Movie,
  isFavorite: Boolean = false,
  onMovieClick: (Int) -> Unit,
  onFavoriteClick : () -> Unit = {}
) {
  Box(
    modifier = Modifier.height(175.dp).clip(RoundedCornerShape(12.dp)),
  ) {
    AsyncImage(
      model=movie.posterUrl,
      contentDescription = movie.title,
      modifier = Modifier.matchParentSize().clickable { onMovieClick(movie.id) },
    )

    IconButton(
      onClick = { onFavoriteClick() }
    , modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(2.dp)
        .size(32.dp)
    ) {
      Icon(
        imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
        contentDescription = "Favorite",
        tint = if (isFavorite) Color.Yellow else Color.White,
        modifier = Modifier.matchParentSize()
      )
    }
  }
}