package com.jean.cinemappcompose.movie.data.repository

import com.jean.cinemappcompose.core.util.inTheaterMovies
import com.jean.cinemappcompose.core.util.upcomingMovies
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMoviesRepository : MoviesRepository {

    override fun getInTheaterMovies(): Flow<Result<List<Movie>>> {
        return flow { emit(Result.success(inTheaterMovies.map { it.toDomain() })) }
    }

    override fun getUpcomingMovies(): Flow<Result<List<Movie>>> {
        return flow { emit(Result.success(upcomingMovies.map { it.toDomain() })) }
    }

}