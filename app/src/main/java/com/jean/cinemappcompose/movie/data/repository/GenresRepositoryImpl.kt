package com.jean.cinemappcompose.movie.data.repository

import com.jean.cinemappcompose.movie.data.datasource.GenresRemoteDataSource
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val genresRemoteDatasource: GenresRemoteDataSource
) : GenresRepository {
    override suspend fun getMovieGenres(): Result<List<Genre>> {
        return try {
            val remoteResponse = genresRemoteDatasource.getMovieGenres().map { genreApiModel ->
                genreApiModel.toDomain()
            }
            Result.success(remoteResponse)
        } catch (exception: Exception) {
            Result.failure(Throwable(exception.message))
        }
    }

}