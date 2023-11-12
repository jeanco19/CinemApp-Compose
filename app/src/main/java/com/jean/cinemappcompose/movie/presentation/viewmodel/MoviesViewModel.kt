package com.jean.cinemappcompose.movie.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.domain.usecase.ConnectivityManagerUseCase
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.core.util.connectivity.ConnectivityStatus
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetInTheaterMoviesUseCase
import com.jean.cinemappcompose.movie.domain.usecase.genre.GetMovieGenresUseCase
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetUpcomingMoviesUseCase
import com.jean.cinemappcompose.profile.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getInTheaterMoviesUseCase: GetInTheaterMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getMovieGenresUseCase: GetMovieGenresUseCase,
    private val connectivityManagerUseCase: ConnectivityManagerUseCase
): ViewModel() {

    var uiState by mutableStateOf(MovieUiState())
        private set

    init {
        getUserData()
        getInTheaterMovies()
        getUpcomingMovies()
        getMovieGenres()
        handleConnectivity()
    }

    fun getUserData() {
        viewModelScope.launch {
            getUserUseCase.invoke().collect { result ->
                result.onSuccess { user ->
                    uiState = uiState.copy(username = user.fullName)
                }
            }
        }
    }

    private fun getInTheaterMovies() {
        uiState = uiState.copy(isLoadingInTheater = true)
        viewModelScope.launch {
            getInTheaterMoviesUseCase.invoke().collect { result ->
                result.onSuccess { movies ->
                    uiState = uiState.copy(
                        isLoadingInTheater = false,
                        inTheaterMovies = movies
                    )
                }.onFailure { throwable ->
                    uiState = uiState.copy(
                        isLoadingInTheater = false,
                        errorInTheaterMovies = throwable.message ?: EMPTY_STRING
                    )
                }
            }
        }
    }

    private fun getUpcomingMovies() {
        uiState = uiState.copy(isLoadingUpcoming = true)
        viewModelScope.launch {
            getUpcomingMoviesUseCase.invoke().collect {
                it.onSuccess { movies ->
                    uiState = uiState.copy(
                        isLoadingUpcoming = false,
                        upcomingMovies = movies
                    )
                }
                it.onFailure { error ->
                    uiState = uiState.copy(
                        isLoadingUpcoming = false,
                        errorUpcomingMovies = error.message ?: EMPTY_STRING
                    )
                }
            }
        }
    }

    private fun getMovieGenres() {
        viewModelScope.launch {
            getMovieGenresUseCase.invoke().collect {
                it.onSuccess { genres ->
                    uiState = uiState.copy(genres = genres)
                }
            }
        }
    }

    private fun handleConnectivity() {
        viewModelScope.launch {
            connectivityManagerUseCase.invoke().collect { status ->
                uiState = uiState.copy(
                    hasConnectivity = status == ConnectivityStatus.AVAILABLE,
                    connectivityMessage = status.message
                )
            }
        }
    }

}