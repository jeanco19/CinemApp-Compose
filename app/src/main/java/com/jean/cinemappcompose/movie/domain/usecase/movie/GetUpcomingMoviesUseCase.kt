package com.jean.cinemappcompose.movie.domain.usecase.movie

import com.jean.cinemappcompose.movie.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface GetUpcomingMoviesUseCase {

    fun invoke(): Flow<Result<List<Movie>>>

}