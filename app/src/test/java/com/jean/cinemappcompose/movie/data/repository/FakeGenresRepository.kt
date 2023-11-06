package com.jean.cinemappcompose.movie.data.repository

import com.jean.cinemappcompose.core.util.genres
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGenresRepository : GenresRepository {
    override fun getMovieGenres(): Flow<Result<List<Genre>>> {
        return flow { emit(Result.success(genres.map { it.toDomain() })) }
    }

}