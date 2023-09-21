package com.jean.cinemappcompose.movie.di

import com.jean.cinemappcompose.movie.data.datasource.genre.GenresLocalDataSource
import com.jean.cinemappcompose.movie.data.datasource.genre.GenresRemoteDataSource
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesLocalDataSource
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesRemoteDataSource
import com.jean.cinemappcompose.movie.data.repository.GenresRepositoryImpl
import com.jean.cinemappcompose.movie.data.repository.MoviesRepositoryImpl
import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesRemoteDataSource, moviesLocalDataSource)
    }

    @Singleton
    @Provides
    fun provideGenresRepository(
        genresRemoteDataSource: GenresRemoteDataSource,
        genresLocalDataSource: GenresLocalDataSource
    ): GenresRepository {
        return GenresRepositoryImpl(genresRemoteDataSource, genresLocalDataSource)
    }

}