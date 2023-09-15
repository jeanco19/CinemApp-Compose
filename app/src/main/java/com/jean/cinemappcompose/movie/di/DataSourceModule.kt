package com.jean.cinemappcompose.movie.di

import com.jean.cinemappcompose.core.data.network.MoviesApiService
import com.jean.cinemappcompose.movie.data.datasource.GenresRemoteDataSource
import com.jean.cinemappcompose.movie.data.datasource.GenresRemoteDataSourceImpl
import com.jean.cinemappcompose.movie.data.datasource.MoviesRemoteDataSource
import com.jean.cinemappcompose.movie.data.datasource.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(
        moviesApiService: MoviesApiService,
        coroutineDispatcher: CoroutineDispatcher
    ): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(moviesApiService, coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun provideGenresRemoteDataSource(
        moviesApiService: MoviesApiService,
        coroutineDispatcher: CoroutineDispatcher
    ): GenresRemoteDataSource {
        return GenresRemoteDataSourceImpl(moviesApiService, coroutineDispatcher)
    }

}