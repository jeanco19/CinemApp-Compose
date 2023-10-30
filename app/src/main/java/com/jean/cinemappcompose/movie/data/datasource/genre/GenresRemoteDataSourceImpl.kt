package com.jean.cinemappcompose.movie.data.datasource.genre

import com.jean.cinemappcompose.core.data.network.MoviesApiService
import com.jean.cinemappcompose.core.data.util.Constants.SPANISH_LANGUAGE_TYPE
import com.jean.cinemappcompose.movie.data.network.model.GenreApiModel
import javax.inject.Inject

class GenresRemoteDataSourceImpl @Inject constructor(
    private val moviesApiService: MoviesApiService
) : GenresRemoteDataSource {

    override suspend fun getMovieGenres(): List<GenreApiModel> {
        return moviesApiService.getMovieGenres(SPANISH_LANGUAGE_TYPE).body()?.genres ?: listOf()
    }

}