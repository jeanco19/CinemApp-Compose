package com.jean.cinemappcompose.movie.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.movie.domain.usecase.GetCurrentMovies
import com.jean.cinemappcompose.movie.domain.usecase.GetMovieGenres
import com.jean.cinemappcompose.movie.domain.usecase.GetUpcomingMovies
import com.jean.cinemappcompose.profile.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getCurrentMovies: GetCurrentMovies,
    private val getUpcomingMovies: GetUpcomingMovies,
    private val getMovieGenres: GetMovieGenres
): ViewModel() {

    var uiState by mutableStateOf(MovieUiState())
        private set

    init {
        getUserData()
        getCurrentMovies()
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

    private fun getCurrentMovies() {
        uiState = uiState.copy(isLoadingCurrent = true)
        viewModelScope.launch {
            getCurrentMovies.invoke()
                .onSuccess { movies ->
                    uiState = uiState.copy(
                        isLoadingCurrent = false,
                        currentMovies = movies
                    )
                }
                .onFailure { error ->
                    uiState = uiState.copy(
                        isLoadingCurrent = false,
                        errorCurrentMovies = error.message ?: EMPTY_STRING
                    )
                }
        }
    }

    private fun getUpcomingMovies() {
        uiState = uiState.copy(isLoadingUpcoming = true)
        viewModelScope.launch {
            getUpcomingMovies.invoke()
                .onSuccess { movies ->
                    uiState = uiState.copy(
                        isLoadingUpcoming = false,
                        upcomingMovies = movies
                    )
                }
                .onFailure { error ->
                    uiState = uiState.copy(
                        isLoadingUpcoming = false,
                        errorUpcomingMovies = error.message ?: EMPTY_STRING
                    )
                }
        }
    }

    private fun getMovieGenres() {
        viewModelScope.launch {
            getMovieGenres.invoke()
                .onSuccess { genres ->
                    uiState = uiState.copy(genres = genres)
                }
        }
    }

}