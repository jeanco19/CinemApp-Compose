package com.jean.cinemappcompose.movie.domain.usecase.movie

import com.jean.cinemappcompose.core.util.upcomingMovies
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetUpcomingMoviesUseCase : GetUpcomingMoviesUseCase {
    override fun invoke(): Flow<Result<List<Movie>>> {
        return flow { emit(Result.success(upcomingMovies.map { it.toDomain() })) }
    }

}