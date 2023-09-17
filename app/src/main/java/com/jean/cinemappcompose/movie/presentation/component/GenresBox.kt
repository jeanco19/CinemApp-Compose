package com.jean.cinemappcompose.movie.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.movie.domain.models.Genre

@Composable
fun GenresBox(
    genres: List<Genre>,
    onGenreClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyRow {
            items(genres) { genre ->
                TextButton(onClick = { onGenreClicked() }) {
                    Text(
                        text = genre.name.uppercase(),
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}