package com.jean.cinemappcompose.movie.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jean.cinemappcompose.movie.data.database.entities.InTheaterMovieEntity
import com.jean.cinemappcompose.movie.data.database.entities.UpcomingMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertInTheaterMovies(movies: List<InTheaterMovieEntity>)

    @Upsert
    suspend fun upsertUpcomingMovies(movies: List<UpcomingMoviesEntity>)

    @Query("SELECT * FROM in_theater_movies")
    fun getInTheaterMovies(): Flow<List<InTheaterMovieEntity>>

    @Query("SELECT * FROM upcoming_movies")
    fun getUpcomingMovies(): Flow<List<UpcomingMoviesEntity>>

}