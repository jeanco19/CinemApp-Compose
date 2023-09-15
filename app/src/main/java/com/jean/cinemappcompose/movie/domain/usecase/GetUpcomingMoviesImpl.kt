package com.jean.cinemappcompose.movie.domain.usecase

import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import javax.inject.Inject

class GetUpcomingMoviesImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetUpcomingMovies {

    override suspend fun invoke(): Result<List<Movie>> {
        return moviesRepository.getUpcomingMovies()
    }

}