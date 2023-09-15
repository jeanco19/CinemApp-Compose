package com.jean.cinemappcompose.movie.presentation.viewmodel

import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.models.Movie

data class MovieUiState(
    val username: String = EMPTY_STRING,
    val genres: List<Genre> = listOf(),
    val isLoadingCurrent: Boolean = false,
    val isLoadingUpcoming: Boolean = false,
    val currentMovies: List<Movie> = listOf(),
    val upcomingMovies: List<Movie> = listOf(),
    val errorCurrentMovies: String = EMPTY_STRING,
    val errorUpcomingMovies: String = EMPTY_STRING,
)