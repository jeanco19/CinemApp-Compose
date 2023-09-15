package com.jean.cinemappcompose.movie.data.mapper

import com.jean.cinemappcompose.movie.data.network.model.GenreApiModel
import com.jean.cinemappcompose.movie.domain.models.Genre

fun GenreApiModel.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}