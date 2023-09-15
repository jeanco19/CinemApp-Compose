package com.jean.cinemappcompose.movie.data.repository

import com.jean.cinemappcompose.movie.data.datasource.MoviesRemoteDataSource
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override suspend fun getCurrentMovies(): Result<List<Movie>> {
        return try {
            val remoteResponse = moviesRemoteDataSource.getCurrentMovies().map { movieApiModel ->
                movieApiModel.toDomain()
            }
            Result.success(remoteResponse)
        } catch (exception: Exception) {
            Result.failure(Throwable(exception.message))
        }
    }

    override suspend fun getUpcomingMovies(): Result<List<Movie>> {
        return try {
            val remoteResponse = moviesRemoteDataSource.getUpcomingMovies().map { movieApiModel ->
                movieApiModel.toDomain()
            }
            Result.success(remoteResponse)
        } catch (exception: Exception) {
            Result.failure(Throwable(exception.message))
        }
    }

}