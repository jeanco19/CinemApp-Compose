package com.jean.cinemappcompose.movie.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jean.cinemappcompose.core.domain.usecase.ConnectivityManagerUseCase
import com.jean.cinemappcompose.core.domain.usecase.ConnectivityManagerUseCaseImpl
import com.jean.cinemappcompose.core.util.MainCoroutineRule
import com.jean.cinemappcompose.core.util.connectivity.FakeNetworkConnectivityObserver
import com.jean.cinemappcompose.core.util.genres
import com.jean.cinemappcompose.core.util.inTheaterMovies
import com.jean.cinemappcompose.core.util.upcomingMovies
import com.jean.cinemappcompose.core.util.userData
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.usecase.genre.FakeGetMovieGenresUseCase
import com.jean.cinemappcompose.movie.domain.usecase.genre.GetMovieGenresUseCase
import com.jean.cinemappcompose.movie.domain.usecase.movie.FakeGetInTheaterMoviesUseCase
import com.jean.cinemappcompose.movie.domain.usecase.movie.FakeGetUpcomingMoviesUseCase
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetInTheaterMoviesUseCase
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetUpcomingMoviesUseCase
import com.jean.cinemappcompose.profile.domain.usecase.FakeGetUserUseCase
import com.jean.cinemappcompose.profile.domain.usecase.GetUserUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get: Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var getInTheaterMoviesUseCase: GetInTheaterMoviesUseCase
    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
    private lateinit var getMovieGenresUseCase: GetMovieGenresUseCase
    private lateinit var connectivityManagerUseCase: ConnectivityManagerUseCase
    private lateinit var fakeNetworkConnectivityObserver: FakeNetworkConnectivityObserver

    private lateinit var sut: MoviesViewModel

    @Before
    fun setup() {
        getUserUseCase = FakeGetUserUseCase()
        getInTheaterMoviesUseCase = FakeGetInTheaterMoviesUseCase()
        getUpcomingMoviesUseCase = FakeGetUpcomingMoviesUseCase()
        getMovieGenresUseCase = FakeGetMovieGenresUseCase()
        fakeNetworkConnectivityObserver = FakeNetworkConnectivityObserver()
        connectivityManagerUseCase = ConnectivityManagerUseCaseImpl(fakeNetworkConnectivityObserver)

        sut = MoviesViewModel(
            getUserUseCase,
            getInTheaterMoviesUseCase,
            getUpcomingMoviesUseCase,
            getMovieGenresUseCase,
            connectivityManagerUseCase
        )
    }

    @Test
    fun `validate when user enter to home screen, then the user fullname is showed`() = runTest {
        val expectedName = userData.fullName

        val resultName = sut.uiState.username

        Truth.assertThat(resultName).isEqualTo(expectedName)
    }

    @Test
    fun `validate when user enter to home screen, then the in theater movies is showed`() = runTest {
        val expectedMovies = inTheaterMovies.map { it.toDomain() }

        val resultLoader = sut.uiState.isLoadingInTheater
        val resultMovies = sut.uiState.inTheaterMovies
        val resultErrorMessage = sut.uiState.errorInTheaterMovies

        Truth.assertThat(resultLoader).isFalse()
        Truth.assertThat(resultMovies).isEqualTo(expectedMovies)
        Truth.assertThat(resultErrorMessage).isEmpty()
    }

    @Test
    fun `validate when user enter to home screen, then the upcoming movies is showed`() = runTest {
        val expectedMovies = upcomingMovies.map { it.toDomain() }

        val resultLoader = sut.uiState.isLoadingUpcoming
        val resultMovies = sut.uiState.upcomingMovies
        val resultErrorMessage = sut.uiState.errorUpcomingMovies

        Truth.assertThat(resultLoader).isFalse()
        Truth.assertThat(resultMovies).isEqualTo(expectedMovies)
        Truth.assertThat(resultErrorMessage).isEmpty()
    }

    @Test
    fun `validate when user enter to home screen, then the movie genres are showed`() = runTest {
        val expectedGenres = genres.map { it.toDomain() }

        val resultGenres = sut.uiState.genres

        Truth.assertThat(resultGenres).isEqualTo(expectedGenres)
    }

}