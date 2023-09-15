package com.jean.cinemappcompose.core.data.network.model

data class MoviesResponse(
    val page: Int,
    val results: List<MovieApiModel>
)