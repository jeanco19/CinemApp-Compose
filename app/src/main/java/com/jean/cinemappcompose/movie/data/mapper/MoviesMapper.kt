package com.jean.cinemappcompose.movie.data.mapper

import com.jean.cinemappcompose.core.data.network.model.MovieApiModel
import com.jean.cinemappcompose.core.data.util.Constants.BASE_URL
import com.jean.cinemappcompose.core.data.util.Constants.IMAGE_URL
import com.jean.cinemappcompose.movie.domain.models.Movie

fun MovieApiModel.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = IMAGE_URL + posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genreIds = genreIds
    )
}