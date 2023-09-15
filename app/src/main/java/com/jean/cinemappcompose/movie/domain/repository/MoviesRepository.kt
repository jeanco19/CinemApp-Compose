package com.jean.cinemappcompose.movie.domain.repository

import com.jean.cinemappcompose.movie.domain.models.Movie

interface MoviesRepository {

    suspend fun getCurrentMovies(): Result<List<Movie>>

    suspend fun getUpcomingMovies(): Result<List<Movie>>

}