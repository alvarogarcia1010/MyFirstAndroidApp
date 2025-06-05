package com.agarcia.myfirstandroidapp.data.remote.Movie

import com.agarcia.myfirstandroidapp.data.database.entities.FavoriteMovieEntity
import com.agarcia.myfirstandroidapp.data.database.entities.MovieEntity
import com.agarcia.myfirstandroidapp.data.model.FavoriteMovie
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("genre_ids")
    val genre_ids: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
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

fun MovieResponse.toEntity(): MovieEntity  {
    return MovieEntity(
        id = id,
        adult = adult,
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count
    )
}