package com.jean.cinemappcompose.movie.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetInTheaterMovies
import com.jean.cinemappcompose.movie.domain.usecase.genre.GetMovieGenres
import com.jean.cinemappcompose.movie.domain.usecase.movie.GetUpcomingMovies
import com.jean.cinemappcompose.profile.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getInTheaterMovies: GetInTheaterMovies,
    private val getUpcomingMovies: GetUpcomingMovies,
    private val getMovieGenres: GetMovieGenres
): ViewModel() {

    var uiState by mutableStateOf(MovieUiState())
        private set

    init {
        getUserData()
        getInTheaterMovies()
        getUpcomingMovies()
        getMovieGenres()
    }

    private fun getUserData() {
        viewModelScope.launch {
            getUserUseCase.invoke().collect { result ->
                result.onSuccess { user ->
                    uiState = uiState.copy(username = user.fullName)
                }
            }
        }
    }

    private fun getInTheaterMovies() {
        uiState = uiState.copy(isLoadingCurrent = true)
        viewModelScope.launch {
            getInTheaterMovies.invoke().collect { result ->
                result.onSuccess { movies ->
                    uiState = uiState.copy(
                        isLoadingCurrent = false,
                        inTheaterMovies = movies
                    )
                }.onFailure { throwable ->
                    uiState = uiState.copy(
                        isLoadingCurrent = false,
                        errorCurrentMovies = throwable.message ?: EMPTY_STRING
                    )
                }
            }
        }
    }

    private fun getUpcomingMovies() {
        uiState = uiState.copy(isLoadingUpcoming = true)
        viewModelScope.launch {
            getUpcomingMovies.invoke().collect {
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
            getMovieGenres.invoke().collect {
                it.onSuccess { genres ->
                    uiState = uiState.copy(genres = genres)
                }
            }
        }
    }

}