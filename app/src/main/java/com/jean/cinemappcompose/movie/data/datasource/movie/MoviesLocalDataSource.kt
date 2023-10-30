package com.jean.cinemappcompose.movie.data.datasource.movie

import com.jean.cinemappcompose.movie.data.database.entities.InTheaterMovieEntity
import com.jean.cinemappcompose.movie.data.database.entities.UpcomingMoviesEntity
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    suspend fun insertInTheaterMovies(movies: List<InTheaterMovieEntity>)

    fun getInTheaterMovies(): Flow<List<InTheaterMovieEntity>?>

    suspend fun insertUpcomingMovies(movies: List<UpcomingMoviesEntity>)

    fun getUpcomingMovies(): Flow<List<UpcomingMoviesEntity>?>

}