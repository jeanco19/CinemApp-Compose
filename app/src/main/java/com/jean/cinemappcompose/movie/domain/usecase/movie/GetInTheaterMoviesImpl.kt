package com.jean.cinemappcompose.movie.domain.usecase.movie

import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInTheaterMoviesImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetInTheaterMovies {

    override fun invoke(): Flow<Result<List<Movie>>> {
        return moviesRepository.getInTheaterMovies()
    }

}