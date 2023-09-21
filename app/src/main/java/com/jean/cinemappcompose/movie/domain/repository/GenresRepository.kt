package com.jean.cinemappcompose.movie.domain.repository

import com.jean.cinemappcompose.movie.domain.models.Genre
import kotlinx.coroutines.flow.Flow

interface GenresRepository {

    fun getMovieGenres(): Flow<Result<List<Genre>>>

}