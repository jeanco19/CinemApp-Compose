package com.jean.cinemappcompose.movie.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = state.hasConnectivity) {
        if (state.connectivityMessage != null) {
            snackBarHostState.showSnackbar(
                message = context.getString(state.connectivityMessage),
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        MovieContent(state = state)
    }

}