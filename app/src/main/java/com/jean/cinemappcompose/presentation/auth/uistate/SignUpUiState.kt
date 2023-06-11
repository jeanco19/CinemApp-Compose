package com.jean.cinemappcompose.presentation.auth.uistate

data class SignUpUiState(
    val isLoading: Boolean = false,
    val isSignedUp: Boolean = false,
    val hasEmailError: Boolean = false,
    val hasPasswordError: Boolean = false,
    val errorType: String = ""
)