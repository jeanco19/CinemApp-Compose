package com.jean.cinemappcompose.movie.domain.usecase

import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import javax.inject.Inject

class GetMovieGenresImpl @Inject constructor(
    private val genresRepository: GenresRepository
) : GetMovieGenres {
    override suspend fun invoke(): Result<List<Genre>> {
        return genresRepository.getMovieGenres()
    }

}