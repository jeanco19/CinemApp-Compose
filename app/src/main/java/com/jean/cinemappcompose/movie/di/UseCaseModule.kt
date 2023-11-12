package com.jean.cinemappcompose.movie.di

import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetInTheaterMoviesUseCase
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetInTheaterMoviesUseCaseImpl
import com.jean.cinemappcompose.movie.domain.usecase.genre.GetMovieGenresUseCase
import com.jean.cinemappcompose.movie.domain.usecase.genre.GetMovieGenresUseCaseImpl
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetUpcomingMoviesUseCase
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetUpcomingMoviesUseCaseImpl
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
    ): GetInTheaterMoviesUseCase {
        return GetInTheaterMoviesUseCaseImpl(moviesRepository)
    }

    @Provides
    fun provideGetUpcomingMovies(
        moviesRepository: MoviesRepository
    ): GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCaseImpl(moviesRepository)
    }

    @Provides
    fun provideGetMovieGenres(
        genresRepository: GenresRepository
    ): GetMovieGenresUseCase {
        return GetMovieGenresUseCaseImpl(genresRepository)
    }

}