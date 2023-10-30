package com.jean.cinemappcompose.movie.data.datasource.genre

import com.jean.cinemappcompose.movie.data.database.dao.GenreDao
import com.jean.cinemappcompose.movie.data.database.entities.GenreEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreLocalDataSourceImpl @Inject constructor(
    private val genreDao: GenreDao
) : GenresLocalDataSource {

    override suspend fun insertGenres(genres: List<GenreEntity>) {
        return genreDao.upsertGenres(genres)
    }

    override fun getGenres(): Flow<List<GenreEntity>?> {
        return genreDao.getGenres()
    }

}