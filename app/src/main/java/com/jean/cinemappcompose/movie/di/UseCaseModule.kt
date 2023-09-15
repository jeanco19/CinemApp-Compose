package com.jean.cinemappcompose.movie.di

import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import com.jean.cinemappcompose.movie.domain.usecase.GetCurrentMovies
import com.jean.cinemappcompose.movie.domain.usecase.GetCurrentMoviesImpl
import com.jean.cinemappcompose.movie.domain.usecase.GetMovieGenres
import com.jean.cinemappcompose.movie.domain.usecase.GetMovieGenresImpl
import com.jean.cinemappcompose.movie.domain.usecase.GetUpcomingMovies
import com.jean.cinemappcompose.movie.domain.usecase.GetUpcomingMoviesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCurrentMovies(
        moviesRepository: MoviesRepository
    ): GetCurrentMovies {
        return GetCurrentMoviesImpl(moviesRepository)
    }

    @Provides
    fun provideGetUpcomingMovies(
        moviesRepository: MoviesRepository
    ): GetUpcomingMovies {
        return GetUpcomingMoviesImpl(moviesRepository)
    }

    @Provides
    fun provideGetMovieGenres(
        genresRepository: GenresRepository
    ): GetMovieGenres {
        return GetMovieGenresImpl(genresRepository)
    }

}