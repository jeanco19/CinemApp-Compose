package com.jean.cinemappcompose.movie.domain.usecase.movie

import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetUpcomingMovies {

    override fun invoke(): Flow<Result<List<Movie>>> {
        return moviesRepository.getUpcomingMovies()
    }

}