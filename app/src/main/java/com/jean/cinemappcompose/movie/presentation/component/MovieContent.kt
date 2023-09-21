package com.jean.cinemappcompose.movie.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jean.cinemappcompose.movie.presentation.viewmodel.MovieUiState

@Composable
fun MovieContent(state: MovieUiState) {
    Column {
        WelcomeBox(username = state.username)
        Spacer(modifier = Modifier.height(10.dp))
        GenresBox(
            genres = state.genres,
            onGenreClicked = {}
        )
        MoviesBox(
            isCurrentLoading = state.isLoadingCurrent,
            isUpcomingLoading = state.isLoadingUpcoming,
            inTheaterMovies = state.inTheaterMovies,
            upcomingMovies = state.upcomingMovies
        )
    }
}