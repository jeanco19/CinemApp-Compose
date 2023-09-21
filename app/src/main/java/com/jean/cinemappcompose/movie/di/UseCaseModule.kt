package com.jean.cinemappcompose.movie.di

import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetInTheaterMovies
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetInTheaterMoviesImpl
import com.jean.cinemappcompose.movie.domain.usecase.genre.GetMovieGenres
import com.jean.cinemappcompose.movie.domain.usecase.genre.GetMovieGenresImpl
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetUpcomingMovies
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetUpcomingMoviesImpl
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
    ): GetInTheaterMovies {
        return GetInTheaterMoviesImpl(moviesRepository)
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