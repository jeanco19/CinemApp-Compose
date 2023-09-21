package com.jean.cinemappcompose.movie.domain.usecase.genre

import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieGenresImpl @Inject constructor(
    private val genresRepository: GenresRepository
) : GetMovieGenres {
    override fun invoke(): Flow<Result<List<Genre>>> {
        return genresRepository.getMovieGenres()
    }

}