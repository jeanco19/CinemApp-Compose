package com.jean.cinemappcompose.movie.domain.repository

import com.jean.cinemappcompose.movie.domain.models.Genre

interface GenresRepository {

    suspend fun getMovieGenres(): Result<List<Genre>>

}