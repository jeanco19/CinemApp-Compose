package com.jean.cinemappcompose.movie.domain.repository

import com.jean.cinemappcompose.movie.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getInTheaterMovies(): Flow<Result<List<Movie>>>

    fun getUpcomingMovies(): Flow<Result<List<Movie>>>

}