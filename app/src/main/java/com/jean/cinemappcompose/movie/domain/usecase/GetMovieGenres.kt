package com.jean.cinemappcompose.movie.domain.usecase

import com.jean.cinemappcompose.movie.domain.models.Genre

interface GetMovieGenres {

    suspend fun invoke(): Result<List<Genre>>

}