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
                moviesLocalDataSource.getInTheaterMovies().collect { localData ->
                    if (localData.isNullOrEmpty()) {
                        moviesRemoteDataSource.getInTheaterMovies().map { movieApi ->
                            movieApi.toDomain(MovieType.IN_THEATER)
                        }.also { movies ->
                            moviesLocalDataSource.insertInTheaterMovies(
                                movies.map { it.toInTheaterEntity() }
                            )
                        }

                    }
                    emit(Result.success(localData?.map { it.toDomain() } ?: listOf()))
                }
            } catch (exception: Exception) {
                emit(Result.failure(Throwable(exception.message)))
            }
        }
    }

    override fun getUpcomingMovies(): Flow<Result<List<Movie>>> {
        return flow {
            try {
                moviesLocalDataSource.getUpcomingMovies().collect { localData ->
                    if (localData.isNullOrEmpty()) {
                        moviesRemoteDataSource.getInTheaterMovies().map { movieApi ->
                            movieApi.toDomain(MovieType.UPCOMING)
                        }.also { movies ->
                            moviesLocalDataSource.insertUpcomingMovies(
                                movies.map { it.toUpcomingEntity() }
                            )
                        }

                    }
                    emit(Result.success(localData?.map { it.toDomain() } ?: listOf()))
                }
            } catch (exception: Exception) {
                emit(Result.failure(Throwable(exception.message)))
            }
        }
    }

}