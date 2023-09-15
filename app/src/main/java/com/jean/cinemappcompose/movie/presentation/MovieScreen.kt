package com.jean.cinemappcompose.movie.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.movie.presentation.component.MovieContent
import com.jean.cinemappcompose.movie.presentation.viewmodel.MoviesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    viewModel: MoviesViewModel = hiltViewModel()
) {

    val state = viewModel.uiState

    Scaffold(
        content = {
            MovieContent(state = state)
        }
    )

}