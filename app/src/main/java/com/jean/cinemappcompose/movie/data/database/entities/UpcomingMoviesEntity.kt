package com.jean.cinemappcompose.movie.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jean.cinemappcompose.movie.data.database.utils.DatabaseConstants.UPCOMING_TABLE
import com.jean.cinemappcompose.movie.domain.models.MovieType

@Entity(tableName = UPCOMING_TABLE)
data class UpcomingMoviesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val type: MovieType,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val genreIds: List<Int>
)