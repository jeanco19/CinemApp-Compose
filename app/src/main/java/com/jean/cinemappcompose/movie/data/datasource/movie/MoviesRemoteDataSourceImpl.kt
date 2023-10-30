package com.jean.cinemappcompose.movie.data.datasource.movie

import com.jean.cinemappcompose.core.data.network.MoviesApiService
import com.jean.cinemappcompose.core.data.network.model.MovieApiModel
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApiService: MoviesApiService
) : MoviesRemoteDataSource {

    override suspend fun getInTheaterMovies(): List<MovieApiModel> {
        return moviesApiService.getInTheaterMovies().body()?.results ?: listOf()
    }

    override suspend fun getUpcomingMovies(): List<MovieApiModel> {
        return moviesApiService.getUpcomingMovies().body()?.results ?: listOf()
    }

}