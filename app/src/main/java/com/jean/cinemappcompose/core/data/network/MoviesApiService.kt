package com.jean.cinemappcompose.core.data.network

import com.jean.cinemappcompose.core.data.network.model.MoviesResponse
import com.jean.cinemappcompose.movie.data.network.model.GenresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movie/now_playing")
    suspend fun getInTheaterMovies(): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MoviesResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(@Query("language") language: String): Response<GenresResponse>

}