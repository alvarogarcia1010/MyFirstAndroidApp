package com.agarcia.myfirstandroidapp.data.remote.Movie

import com.agarcia.myfirstandroidapp.data.remote.Responses.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieService {
  @GET("movie/popular?language=es-SV&page=1")
  suspend fun getPopularMovies() : BaseResponse<MovieResponse>

  @GET("movie/popular?language=es-SV&page=1")
  suspend fun getPopularMovies1( @Header("Authorization") authHeader: String ) : BaseResponse<MovieResponse>
}