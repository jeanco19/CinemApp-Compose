package com.jean.cinemappcompose.movie.data.datasource.genre

import com.jean.cinemappcompose.movie.data.database.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

interface GenresLocalDataSource {

    suspend fun insertGenres(genres: List<GenreEntity>)

    fun getGenres(): Flow<List<GenreEntity>?>

}