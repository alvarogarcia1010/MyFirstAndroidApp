package com.agarcia.myfirstandroidapp.data.remote

import com.agarcia.myfirstandroidapp.data.remote.Movie.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
  private const val BASE_URL = "https://api.themoviedb.org/3/"

  val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

  private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  val movieService: MovieService by lazy {
    retrofit.create(MovieService::class.java)
  }
}