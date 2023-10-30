package com.jean.cinemappcompose.movie.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jean.cinemappcompose.movie.data.database.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {
    @Upsert
    suspend fun upsertGenres(genres: List<GenreEntity>)

    @Query("SELECT * FROM genres")
    fun getGenres(): Flow<List<GenreEntity>?>

}