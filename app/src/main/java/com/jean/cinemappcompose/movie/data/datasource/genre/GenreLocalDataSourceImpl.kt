package com.jean.cinemappcompose.movie.data.datasource.genre

import com.jean.cinemappcompose.movie.data.database.dao.GenreDao
import com.jean.cinemappcompose.movie.data.database.entities.GenreEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreLocalDataSourceImpl @Inject constructor(
    private val genreDao: GenreDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GenresLocalDataSource {

    override suspend fun insertGenres(genres: List<GenreEntity>) {
        return withContext(ioDispatcher) {
            genreDao.upsertGenres(genres)
        }
    }

    override fun getGenres(): Flow<List<GenreEntity>> {
        return genreDao.getGenres()
    }

}