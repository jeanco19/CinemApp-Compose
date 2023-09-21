package com.jean.cinemappcompose.movie.data.datasource.movie

import com.jean.cinemappcompose.core.data.network.model.MovieApiModel

interface MoviesRemoteDataSource {

    suspend fun getInTheaterMovies(): List<MovieApiModel>

    suspend fun getUpcomingMovies(): List<MovieApiModel>

}