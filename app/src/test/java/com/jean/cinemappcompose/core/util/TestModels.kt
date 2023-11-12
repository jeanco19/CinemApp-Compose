package com.jean.cinemappcompose.core.util

import com.jean.cinemappcompose.movie.data.database.entities.GenreEntity
import com.jean.cinemappcompose.movie.data.database.entities.InTheaterMovieEntity
import com.jean.cinemappcompose.movie.data.database.entities.UpcomingMoviesEntity
import com.jean.cinemappcompose.movie.domain.models.MovieType
import com.jean.cinemappcompose.profile.domain.model.User

val genres = listOf(
    GenreEntity(
        id = 1,
        name = "action"
    )
)

val inTheaterMovies = listOf(
    InTheaterMovieEntity(
        id = 609681,
        title = "The Marvels",
        type = MovieType.IN_THEATER,
        overview = "Carol Danvers, aka Captain Marvel, has reclaimed her identity from the tyrannical Kree and taken revenge on the Supreme Intelligence. But unintended consequences see Carol shouldering the burden of a destabilized universe. When her duties send her to an anomalous wormhole linked to a Kree revolutionary, her powers become entangled with that of Jersey City super-fan Kamala Khan, aka Ms. Marvel, and Carol’s estranged niece, now S.A.B.E.R. astronaut Captain Monica Rambeau. Together, this unlikely trio must team up and learn to work in concert to save the universe.",
        posterPath = "/y4YMYsGSMwu8e985g0Zbumvqxld.jpg",
        releaseDate = "2023-11-08",
        voteAverage = 0.0,
        genreIds = listOf(28, 12, 278)
    )
)

val upcomingMovies = listOf(
    UpcomingMoviesEntity(
        id = 609681,
        title = "The Marvels",
        type = MovieType.UPCOMING,
        overview = "Carol Danvers, aka Captain Marvel, has reclaimed her identity from the tyrannical Kree and taken revenge on the Supreme Intelligence. But unintended consequences see Carol shouldering the burden of a destabilized universe. When her duties send her to an anomalous wormhole linked to a Kree revolutionary, her powers become entangled with that of Jersey City super-fan Kamala Khan, aka Ms. Marvel, and Carol’s estranged niece, now S.A.B.E.R. astronaut Captain Monica Rambeau. Together, this unlikely trio must team up and learn to work in concert to save the universe.",
        posterPath = "/y4YMYsGSMwu8e985g0Zbumvqxld.jpg",
        releaseDate = "2023-11-08",
        voteAverage = 0.0,
        genreIds = listOf(28, 12, 278)
    )
)

val userData = User(
    id = "1",
    fullName = "Pedro Perez",
    email = "pperez@gmail.com"
)