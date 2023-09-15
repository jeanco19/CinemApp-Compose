package com.jean.cinemappcompose.movie.data.datasource

import com.jean.cinemappcompose.core.data.network.MoviesApiService
import com.jean.cinemappcompose.core.data.network.model.MovieApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApiService: MoviesApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRemoteDataSource {

    override suspend fun getCurrentMovies(): List<MovieApiModel> {
        return withContext(ioDispatcher) {
            moviesApiService.getCurrentMovies().body()?.results ?: listOf()
        }
    }

    override suspend fun getUpcomingMovies(): List<MovieApiModel> {
        return withContext(ioDispatcher) {
            moviesApiService.getUpcomingMovies().body()?.results ?: listOf()
        }
    }

}