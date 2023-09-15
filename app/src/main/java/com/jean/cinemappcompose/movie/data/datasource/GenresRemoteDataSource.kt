package com.jean.cinemappcompose.movie.data.datasource

import com.jean.cinemappcompose.movie.data.network.model.GenreApiModel

interface GenresRemoteDataSource {

    suspend fun getMovieGenres(): List<GenreApiModel>

}