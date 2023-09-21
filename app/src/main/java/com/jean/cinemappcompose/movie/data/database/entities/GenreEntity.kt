package com.jean.cinemappcompose.movie.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jean.cinemappcompose.movie.data.database.utils.DatabaseConstants.GENRE_TABLE

@Entity(tableName = GENRE_TABLE)
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)