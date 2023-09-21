package com.jean.cinemappcompose.movie.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.movie.domain.models.Movie

@Composable
fun MoviesBox(
    isCurrentLoading: Boolean,
    isUpcomingLoading: Boolean,
    inTheaterMovies: List<Movie>,
    upcomingMovies: List<Movie>,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        MovieTypeCard(
            isLoading = isCurrentLoading,
            titleType = stringResource(id = R.string.current_movies_title),
            descriptionType = stringResource(id = R.string.current_movies_description),
            movies = inTheaterMovies,
            onMovieClicked = {},
            onSeeAllClicked = {}
        )
        MovieTypeCard(
            isLoading = isUpcomingLoading,
            titleType = stringResource(id = R.string.upcoming_movies_title),
            descriptionType = stringResource(id = R.string.upcoming_movies_description),
            movies = upcomingMovies,
            onMovieClicked = {},
            onSeeAllClicked = {}
        )
    }
}