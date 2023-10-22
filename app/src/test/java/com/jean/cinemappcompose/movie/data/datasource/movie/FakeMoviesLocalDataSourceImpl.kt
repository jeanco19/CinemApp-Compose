package com.jean.cinemappcompose.movie.data.datasource.movie

import com.jean.cinemappcompose.movie.data.database.entities.InTheaterMovieEntity
import com.jean.cinemappcompose.movie.data.database.entities.UpcomingMoviesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMoviesLocalDataSourceImpl : MoviesLocalDataSource {

    val fakeInTheaterMovies = mutableListOf<InTheaterMovieEntity>()
    val fakeUpcomingMovies = mutableListOf<UpcomingMoviesEntity>()

    override suspend fun insertInTheaterMovies(movies: List<InTheaterMovieEntity>) {
        fakeInTheaterMovies.addAll(movies)
    }

    override fun getInTheaterMovies(): Flow<List<InTheaterMovieEntity>> {
        return flow { emit(fakeInTheaterMovies) }
    }

    override suspend fun insertUpcomingMovies(movies: List<UpcomingMoviesEntity>) {
        fakeUpcomingMovies.addAll(movies)
    }

    override fun getUpcomingMovies(): Flow<List<UpcomingMoviesEntity>> {
        return flow { emit(fakeUpcomingMovies) }
    }

}