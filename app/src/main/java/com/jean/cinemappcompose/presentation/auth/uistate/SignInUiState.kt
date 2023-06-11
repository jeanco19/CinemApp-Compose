package com.jean.cinemappcompose.presentation.auth.uistate

data class SignInUiState(
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val hasEmailError: Boolean = false,
    val hasPasswordError: Boolean = false,
    val errorType: String = ""
)