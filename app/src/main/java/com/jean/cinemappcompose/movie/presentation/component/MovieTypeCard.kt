package com.jean.cinemappcompose.movie.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.core.util.Constants
import com.jean.cinemappcompose.movie.domain.models.Movie

@Composable
fun MovieTypeCard(
    isLoading: Boolean = false,
    titleType: String,
    descriptionType: String,
    movies: List<Movie>,
    errorMessage: String = Constants.EMPTY_STRING,
    onMovieClicked: (Int) -> Unit,
    onSeeAllClicked: (String) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
                items(movies) { movie ->
                    HomeMovieItem(
                        poster = movie.posterPath,
                        onMovieClicked = { onMovieClicked(movie.id) }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentAlignment = Alignment.Center,
            ) {
                if (isLoading) CircularProgressIndicator(modifier = Modifier.height(10.dp))
                if (errorMessage.isNotEmpty()) {
                    Text(text = stringResource(id = R.string.error_retrieving_movies))
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = titleType.uppercase(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = descriptionType,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Button(onClick = { onSeeAllClicked(titleType) }) {
                Text(text = stringResource(id = R.string.see_all_movies_option))
            }
        }
    }
}

@Composable
fun HomeMovieItem(
    modifier: Modifier = Modifier,
    poster: String,
    onMovieClicked: () -> Unit
) {
    AsyncImage(
        modifier = modifier
            .height(140.dp)
            .width(100.dp)
            .padding(horizontal = 5.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable { onMovieClicked() },
        model = com.jean.cinemappcompose.core.data.util.Constants.IMAGE_URL + poster,
        contentDescription = stringResource(id = R.string.home_movie_poster_content_description),
        contentScale = ContentScale.Crop,
    )
}