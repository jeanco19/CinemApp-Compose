package com.jean.cinemappcompose.movie.data.mapper

import com.jean.cinemappcompose.core.data.network.model.MovieApiModel
import com.jean.cinemappcompose.core.data.util.Constants.IMAGE_URL
import com.jean.cinemappcompose.movie.data.database.entities.InTheaterMovieEntity
import com.jean.cinemappcompose.movie.data.database.entities.UpcomingMoviesEntity
import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.models.MovieType

fun MovieApiModel.toDomain(type: MovieType): Movie {
    return Movie(
        id = id,
        title = title,
        type = type,
        overview = overview,
        posterPath = IMAGE_URL + posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genreIds = genreIds
    )
}

fun InTheaterMovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        type = type,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genreIds = genreIds
    )
}

fun UpcomingMoviesEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        type = type,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genreIds = genreIds
    )
}

fun Movie.toInTheaterEntity(): InTheaterMovieEntity {
    return InTheaterMovieEntity(
        id = id,
        title = title,
        type = type,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genreIds = genreIds
    )
}

fun Movie.toUpcomingEntity(): UpcomingMoviesEntity {
    return UpcomingMoviesEntity(
        id = id,
        title = title,
        type = type,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genreIds = genreIds
    )
}