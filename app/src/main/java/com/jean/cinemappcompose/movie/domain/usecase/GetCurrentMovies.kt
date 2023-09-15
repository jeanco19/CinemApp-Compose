package com.jean.cinemappcompose.movie.domain.usecase

import com.jean.cinemappcompose.movie.domain.models.Movie

interface GetCurrentMovies {

    suspend fun invoke(): Result<List<Movie>>

}