package com.agarcia.myfirstandroidapp.data.remote.Responses

import com.agarcia.myfirstandroidapp.data.database.entities.MovieEntity
import com.agarcia.myfirstandroidapp.data.model.Movie

data class MovieResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
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

fun MovieResponse.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        originalTitle = original_title,
        originalLanguage = original_language,
        overview = overview,
        releaseDate = release_date,
        adult = adult,
        genreIds = genre_ids,
        popularity = popularity,
        voteAverage = vote_average,
        voteCount = vote_count,
        video = video,
        backdropUrl = "https://image.tmdb.org/t/p/w500$backdrop_path",
        posterUrl = "https://image.tmdb.org/t/p/w500$poster_path"
    )
}

fun MovieResponse.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        original_title = original_title,
        original_language = original_language,
        overview = overview,
        release_date = release_date,
        adult = adult,
        popularity = popularity,
        vote_average = vote_average,
        vote_count = vote_count,
        video = video,
        backdrop_path = backdrop_path,
        poster_path = poster_path,
    )
}

