package com.jean.cinemappcompose.movie.data.repository

import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesLocalDataSource
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesRemoteDataSource
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.data.mapper.toInTheaterEntity
import com.jean.cinemappcompose.movie.data.mapper.toUpcomingEntity
import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.models.MovieType
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesRepository {

    override fun getInTheaterMovies(): Flow<Result<List<Movie>>> {
        return flow {
            try {
                var remoteData = listOf<Movie>()
                moviesLocalDataSource.getInTheaterMovies().collect { localData ->
                    if (localData.isEmpty()) {
                        remoteData = moviesRemoteDataSource.getInTheaterMovies().map { movieApi ->
                            movieApi.toDomain(MovieType.IN_THEATER)
                        }
                        moviesLocalDataSource.insertInTheaterMovies(remoteData.map { movie ->
                            movie.toInTheaterEntity()
                        })
                    }
                    emit(Result.success(
                        if (localData.isEmpty()) remoteData else localData.map { it.toDomain() }
                    ))
                }
            } catch (exception: Exception) {
                emit(Result.failure(Throwable(exception.message)))
            }
        }
    }

    override fun getUpcomingMovies(): Flow<Result<List<Movie>>> {
        return flow {
            try {
                var remoteData = listOf<Movie>()
                moviesLocalDataSource.getUpcomingMovies().collect { localData ->
                    if (localData.isEmpty()) {
                        remoteData = moviesRemoteDataSource.getUpcomingMovies().map { movieApi ->
                            movieApi.toDomain(MovieType.UPCOMING)
                        }
                        moviesLocalDataSource.insertUpcomingMovies(remoteData.map { movie ->
                            movie.toUpcomingEntity()
                        })
                    }
                    emit(Result.success(
                        if (localData.isEmpty()) remoteData else localData.map { it.toDomain() }
                    ))
                }
            } catch (exception: Exception) {
                emit(Result.failure(Throwable(exception.message)))
            }
        }
    }

}