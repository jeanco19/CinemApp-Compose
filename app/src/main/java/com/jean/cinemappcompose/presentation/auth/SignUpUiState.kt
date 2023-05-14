package com.jean.cinemappcompose.presentation.auth

data class SignUpUiState(
    val isLoading: Boolean = false,
    val isSignedUp: Boolean = false,
    val hasEmailError: Boolean = false,
    val hasPasswordError: Boolean = false,
    val errorFieldMessage: String = "",
    val errorMessage: String = ""
)