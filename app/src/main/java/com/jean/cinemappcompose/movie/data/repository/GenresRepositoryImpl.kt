package com.jean.cinemappcompose.movie.data.repository

import com.jean.cinemappcompose.movie.data.datasource.genre.GenresLocalDataSource
import com.jean.cinemappcompose.movie.data.datasource.genre.GenresRemoteDataSource
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.data.mapper.toGenreEntity
import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val genresRemoteDatasource: GenresRemoteDataSource,
    private val genresLocalDataSource: GenresLocalDataSource
) : GenresRepository {

    override fun getMovieGenres(): Flow<Result<List<Genre>>> {
        return flow {
            genresLocalDataSource.getGenres().collect { genreEntities ->
                if (genreEntities.isNullOrEmpty()) {
                    genresRemoteDatasource.getMovieGenres().map { genreApiModel ->
                        genreApiModel.toDomain()
                    }.also { genres ->
                        genresLocalDataSource.insertGenres(genres.map { it.toGenreEntity() })
                    }
                }
                emit(Result.success(genreEntities?.map { it.toDomain() } ?: listOf()))
            }
        }.catch { throwable ->
            emit(Result.failure(throwable))
        }
    }

}