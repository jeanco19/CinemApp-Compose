package com.jean.cinemappcompose.movie.data.datasource

import com.jean.cinemappcompose.core.data.network.model.MovieApiModel

interface MoviesRemoteDataSource {

    suspend fun getCurrentMovies(): List<MovieApiModel>

    suspend fun getUpcomingMovies(): List<MovieApiModel>

}