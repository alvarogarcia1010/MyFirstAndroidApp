package com.agarcia.myfirstandroidapp.data.model

data class Movie(
  val id: Int,
  val title: String,
  val originalTitle: String,
  val originalLanguage: String,
  val overview: String,
  val releaseDate: String,
  val adult: Boolean,
  val genreIds: List<Int>,
  val popularity: Double,
  val voteAverage: Double,
  val voteCount: Int,
  val video: Boolean,
  val backdropUrl: String,
  val posterUrl: String
)

fun Movie.toFavoriteMovie(): FavoriteMovie {
  return FavoriteMovie(
    id = id,
    movieId = id,
    title = title,
    posterUrl = posterUrl
  )
}