package com.jean.cinemappcompose.movie.data.datasource.genre

import com.jean.cinemappcompose.movie.data.database.entities.GenreEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGenreLocalDataSourceImpl : GenresLocalDataSource {

    val fakeGenres = mutableListOf<GenreEntity>()

    override suspend fun insertGenres(genres: List<GenreEntity>) {
        fakeGenres.addAll(genres)
    }

    override fun getGenres(): Flow<List<GenreEntity>> {
        return flow { emit(fakeGenres) }
    }

}