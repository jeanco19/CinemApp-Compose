package com.jean.cinemappcompose.movie.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val genreIds: List<Int>
)