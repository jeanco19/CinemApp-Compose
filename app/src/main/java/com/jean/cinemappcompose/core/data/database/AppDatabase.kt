package com.jean.cinemappcompose.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jean.cinemappcompose.movie.data.database.dao.GenreDao
import com.jean.cinemappcompose.movie.data.database.dao.MovieDao
import com.jean.cinemappcompose.movie.data.database.entities.GenreEntity
import com.jean.cinemappcompose.movie.data.database.entities.InTheaterMovieEntity
import com.jean.cinemappcompose.movie.data.database.entities.UpcomingMoviesEntity
import com.jean.cinemappcompose.movie.data.database.typeconverter.MovieTypeConverter

@TypeConverters(MovieTypeConverter::class)
@Database(
    entities = [InTheaterMovieEntity::class, UpcomingMoviesEntity::class, GenreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun genreDao(): GenreDao

}