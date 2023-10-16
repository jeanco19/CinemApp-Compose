package com.jean.cinemappcompose.movie.data.datasource.genre

import com.jean.cinemappcompose.core.data.network.MoviesApiService
import com.jean.cinemappcompose.core.data.util.Constants.SPANISH_LANGUAGE_TYPE
import com.jean.cinemappcompose.movie.data.network.model.GenreApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenresRemoteDataSourceImpl @Inject constructor(
    private val moviesApiService: MoviesApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GenresRemoteDataSource {

    override suspend fun getMovieGenres(): List<GenreApiModel> {
        return withContext(ioDispatcher) {
            moviesApiService.getMovieGenres(SPANISH_LANGUAGE_TYPE).body()?.genres ?: listOf()
        }
    }

}