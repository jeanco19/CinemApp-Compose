package com.jean.cinemappcompose.movie.domain.usecase.genre

import com.jean.cinemappcompose.movie.domain.models.Genre
import kotlinx.coroutines.flow.Flow

interface GetMovieGenres {

    fun invoke(): Flow<Result<List<Genre>>>

}