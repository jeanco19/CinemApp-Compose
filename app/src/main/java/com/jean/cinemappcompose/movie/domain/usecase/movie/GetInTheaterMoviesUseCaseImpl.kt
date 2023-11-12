package com.jean.cinemappcompose.movie.domain.usecase.movie

import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInTheaterMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetInTheaterMoviesUseCase {

    override fun invoke(): Flow<Result<List<Movie>>> {
        return moviesRepository.getInTheaterMovies()
    }

}