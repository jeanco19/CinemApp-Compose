package com.jean.cinemappcompose.presentation.movie.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.presentation.common.component.DefaultButton
import com.jean.cinemappcompose.presentation.movie.viewmodel.MovieViewModel

@Composable
fun MovieScreen(viewModel: MovieViewModel = hiltViewModel()) {
    Scaffold(
        content = {
            Column(modifier = Modifier.padding(it)) {
                Text(text = "Movie Screen")
                Spacer(modifier = Modifier.height(30.dp))
                DefaultButton(
                    label = "Cerrar Sesión",
                    onButtonClicked = {
                        viewModel.doSignOut()
                    }
                )
            }
        }
    )
}