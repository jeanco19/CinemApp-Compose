package com.jean.cinemappcompose.core.di

import android.content.Context
import androidx.room.Room
import com.jean.cinemappcompose.core.data.database.AppDatabase
import com.jean.cinemappcompose.movie.data.database.dao.GenreDao
import com.jean.cinemappcompose.movie.data.database.dao.MovieDao
import com.jean.cinemappcompose.movie.data.database.typeconverter.MovieTypeConverter
import com.jean.cinemappcompose.movie.data.database.utils.DatabaseConstants.CINEMAPP_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, CINEMAPP_DATABASE)
            .addTypeConverter(MovieTypeConverter())
            .build()

    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    fun provideGenreDao(database: AppDatabase): GenreDao {
        return database.genreDao()
    }

}