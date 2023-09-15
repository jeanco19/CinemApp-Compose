package com.jean.cinemappcompose.movie.di

import com.jean.cinemappcompose.movie.data.datasource.GenresRemoteDataSource
import com.jean.cinemappcompose.movie.data.datasource.MoviesRemoteDataSource
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
        moviesRemoteDataSource: MoviesRemoteDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGenresRepository(
        genresRemoteDataSource: GenresRemoteDataSource
    ): GenresRepository {
        return GenresRepositoryImpl(genresRemoteDataSource)
    }

}