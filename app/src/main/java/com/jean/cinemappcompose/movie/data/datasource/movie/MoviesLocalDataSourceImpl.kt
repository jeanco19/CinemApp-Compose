package com.jean.cinemappcompose.movie.data.datasource.movie

import com.jean.cinemappcompose.movie.data.database.dao.MovieDao
import com.jean.cinemappcompose.movie.data.database.entities.InTheaterMovieEntity
import com.jean.cinemappcompose.movie.data.database.entities.UpcomingMoviesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MoviesLocalDataSource {

    override suspend fun insertInTheaterMovies(movies: List<InTheaterMovieEntity>) {
        return movieDao.upsertInTheaterMovies(movies)
    }

    override fun getInTheaterMovies(): Flow<List<InTheaterMovieEntity>?> {
        return movieDao.getInTheaterMovies()
    }

    override suspend fun insertUpcomingMovies(movies: List<UpcomingMoviesEntity>) {
        return movieDao.upsertUpcomingMovies(movies)
    }

    override fun getUpcomingMovies(): Flow<List<UpcomingMoviesEntity>?> {
        return movieDao.getUpcomingMovies()
    }

}