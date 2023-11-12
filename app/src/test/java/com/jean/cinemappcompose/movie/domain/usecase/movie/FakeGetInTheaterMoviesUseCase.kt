package com.jean.cinemappcompose.movie.domain.usecase.movie

import com.jean.cinemappcompose.core.util.inTheaterMovies
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetInTheaterMoviesUseCase : GetInTheaterMoviesUseCase {
    override fun invoke(): Flow<Result<List<Movie>>> {
        return flow { emit(Result.success(inTheaterMovies.map { it.toDomain() })) }
    }

}