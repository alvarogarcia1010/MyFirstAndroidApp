package com.agarcia.myfirstandroidapp.data.remote.Movie

import com.agarcia.myfirstandroidapp.data.remote.Responses.BaseResponse
import com.agarcia.myfirstandroidapp.data.remote.Responses.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieService {
  @GET("movie/popular?language=es-SV&page=1")
  suspend fun getPopularMovies() : BaseResponse<MovieResponse>
}