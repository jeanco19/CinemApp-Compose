package com.jean.cinemappcompose.movie.domain.usecase.movie

import com.jean.cinemappcompose.movie.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface GetInTheaterMoviesUseCase {

    fun invoke(): Flow<Result<List<Movie>>>

}