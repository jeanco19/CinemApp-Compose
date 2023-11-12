package com.jean.cinemappcompose.movie.presentation.viewmodel

import androidx.annotation.StringRes
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.models.Movie

data class MovieUiState(
    val username: String = EMPTY_STRING,
    val genres: List<Genre> = listOf(),
    val isLoadingInTheater: Boolean = false,
    val isLoadingUpcoming: Boolean = false,
    val inTheaterMovies: List<Movie> = listOf(),
    val upcomingMovies: List<Movie> = listOf(),
    val errorInTheaterMovies: String = EMPTY_STRING,
    val errorUpcomingMovies: String = EMPTY_STRING,
    val hasConnectivity: Boolean = true,
    @StringRes val connectivityMessage: Int? = null
)