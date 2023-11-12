package com.jean.cinemappcompose.movie.domain.usecase.genre

import com.jean.cinemappcompose.core.util.genres
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetMovieGenresUseCase : GetMovieGenresUseCase {
    override fun invoke(): Flow<Result<List<Genre>>> {
        return flow { emit(Result.success(genres.map { it.toDomain() })) }
    }

}