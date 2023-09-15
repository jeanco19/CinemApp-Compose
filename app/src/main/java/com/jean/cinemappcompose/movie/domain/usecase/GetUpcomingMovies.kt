package com.jean.cinemappcompose.movie.domain.usecase

import com.jean.cinemappcompose.movie.domain.models.Movie

interface GetUpcomingMovies {

    suspend fun invoke(): Result<List<Movie>>

}