package com.jean.cinemappcompose.movie.domain.usecase

import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import javax.inject.Inject

class GetCurrentMoviesImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetCurrentMovies {

    override suspend fun invoke(): Result<List<Movie>> {
        return moviesRepository.getCurrentMovies()
    }

}