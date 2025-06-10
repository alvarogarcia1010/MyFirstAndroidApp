package com.agarcia.myfirstandroidapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agarcia.myfirstandroidapp.data.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val adult: Boolean,
  val backdrop_path: String,
  val original_language: String,
  val original_title: String,
  val overview: String,
  val popularity: Double,
  val poster_path: String,
  val release_date: String,
  val title: String,
  val video: Boolean,
  val vote_average: Double,
  val vote_count: Int
)

fun MovieEntity.toDomain(): Movie {
  return Movie(
    id = id,
    title = title,
    originalTitle = original_title,
    originalLanguage = original_language,
    overview = overview,
    releaseDate = release_date,
    adult = adult,
    genreIds = emptyList(), // Genre IDs are not included in MovieEntity
    popularity = popularity,
    voteAverage = vote_average,
    voteCount = vote_count,
    video = video,
    backdropUrl = "https://image.tmdb.org/t/p/w500$backdrop_path",
    posterUrl = "https://image.tmdb.org/t/p/w500$poster_path"
  )
}