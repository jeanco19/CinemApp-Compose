package com.jean.cinemappcompose.movie.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jean.cinemappcompose.core.data.util.Constants.IMAGE_URL
import com.jean.cinemappcompose.core.presentation.component.AppNameText
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.presentation.viewmodel.MovieUiState

@Composable
fun MovieContent(state: MovieUiState) {
    Column {
        WelcomeBox(username = "Jean Álvarez")
        Spacer(modifier = Modifier.height(10.dp))
        GenresBox(
            genres = state.genres,
            onGenreClicked = {}
        )
        MoviesBox(
            isCurrentLoading = state.isLoadingCurrent,
            isUpcomingLoading = state.isLoadingUpcoming,
            currentMovies = state.currentMovies,
            upcomingMovies = state.upcomingMovies
        )
    }
}

@Composable
fun WelcomeBox(username: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AppNameText(
                size = 28.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            modifier = Modifier.padding(top = 30.dp, start = 10.dp, bottom = 5.dp),
            text = "Hola $username",
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
            text = "Encuentra información sobre las películas de tu preferencia.",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.ExtraLight
        )
    }
}

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

@Composable
fun MoviesBox(
    isCurrentLoading: Boolean,
    isUpcomingLoading: Boolean,
    currentMovies: List<Movie>,
    upcomingMovies: List<Movie>,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        MovieTypeCard(
            isLoading = isCurrentLoading,
            titleType = "Recientes",
            descriptionType = "Películas actualmente en cines",
            movies = currentMovies,
            onMovieClicked = {},
            onSeeAllClicked = {}
        )
        MovieTypeCard(
            isLoading = isUpcomingLoading,
            titleType = "Estrenos",
            descriptionType = "Proximos estrenos en cines",
            movies = upcomingMovies,
            onMovieClicked = {},
            onSeeAllClicked = {}
        )
    }
}

@Composable
fun MovieTypeCard(
    isLoading: Boolean = false,
    titleType: String,
    descriptionType: String,
    movies: List<Movie>,
    errorMessage: String = EMPTY_STRING,
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
                if (errorMessage.isNotEmpty()) Text(text = "Ha ocurrido un error al recuperar las películas.")
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
                Text(text = "Ver Todas")
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
        model = IMAGE_URL + poster,
        contentDescription = "Home Movie Poster",
        contentScale = ContentScale.Crop,
    )
}

private fun getGenres(): List<Genre> {
    return listOf(
        Genre(
            id = 28,
            name = "Acción"
        ),
        Genre(
            id = 12,
            name = "Aventura"
        ),
        Genre(
            id = 16,
            name = "Animación"
        ),
        Genre(
            id = 35,
            name = "Comedia"
        ),
    )
}

private fun getMovies(): List<Movie> {
    return listOf(
        Movie(
            genreIds = listOf(28, 878, 27),
            id = 615656,
            overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
            posterPath = "/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
            releaseDate = "2023-08-02",
            title = "Meg 2: The Trench",
            voteAverage = 7.1
        ),
        Movie(
            genreIds = listOf(12, 28, 14),
            id = 335977,
            overview = "Finding himself in a new era, and approaching retirement, Indy wrestles with fitting into a world that seems to have outgrown him. But as the tentacles of an all-too-familiar evil return in the form of an old rival, Indy must don his hat and pick up his whip once more to make sure an ancient and powerful artifact doesn't fall into the wrong hands.",
            posterPath = "/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg",
            releaseDate = "2023-06-28",
            title = "Indiana Jones and the Dial of Destiny",
            voteAverage = 6.7
        )
    )
}