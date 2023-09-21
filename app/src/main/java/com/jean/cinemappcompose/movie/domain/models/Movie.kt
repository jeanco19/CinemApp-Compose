package com.jean.cinemappcompose.movie.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val type: MovieType,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val genreIds: List<Int>
)

enum class MovieType {
    IN_THEATER,
    UPCOMING
}