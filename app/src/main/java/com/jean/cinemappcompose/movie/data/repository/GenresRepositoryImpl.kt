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
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val genresRemoteDatasource: GenresRemoteDataSource,
    private val genresLocalDataSource: GenresLocalDataSource
) : GenresRepository {

    override fun getMovieGenres(): Flow<Result<List<Genre>>> {
        return flow {
            val remoteData = genresRemoteDatasource.getMovieGenres().map { genreApiModel ->
                genreApiModel.toDomain()
            }.also { genres ->
                genresLocalDataSource.insertGenres(genres.map { genre -> genre.toGenreEntity() })
            }

            genresLocalDataSource.getGenres().map { genresEntity ->
                val localData = genresEntity.map { genreEntity -> genreEntity.toDomain() }
                emit(Result.success(localData.ifEmpty { remoteData }))
            }
        }.catch { throwable ->
            emit(Result.failure(throwable))
        }
    }

}