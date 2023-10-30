package com.jean.cinemappcompose.movie.di

import com.jean.cinemappcompose.core.data.network.MoviesApiService
import com.jean.cinemappcompose.movie.data.database.dao.GenreDao
import com.jean.cinemappcompose.movie.data.database.dao.MovieDao
import com.jean.cinemappcompose.movie.data.datasource.genre.GenreLocalDataSourceImpl
import com.jean.cinemappcompose.movie.data.datasource.genre.GenresLocalDataSource
import com.jean.cinemappcompose.movie.data.datasource.genre.GenresRemoteDataSource
import com.jean.cinemappcompose.movie.data.datasource.genre.GenresRemoteDataSourceImpl
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesLocalDataSource
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesLocalDataSourceImpl
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesRemoteDataSource
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(
        moviesApiService: MoviesApiService
    ): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(moviesApiService)
    }

    @Singleton
    @Provides
    fun provideGenresRemoteDataSource(
        moviesApiService: MoviesApiService
    ): GenresRemoteDataSource {
        return GenresRemoteDataSourceImpl(moviesApiService)
    }

    @Singleton
    @Provides
    fun provideMoviesLocalDataSource(
        movieDao: MovieDao
    ): MoviesLocalDataSource {
        return MoviesLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideGenresLocalDataSource(
        genreDao: GenreDao
    ): GenresLocalDataSource {
        return GenreLocalDataSourceImpl(genreDao)
    }

}