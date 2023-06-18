package com.jean.cinemappcompose.presentation.auth.uistate

data class RestartPasswordUiState(
    val isLoading: Boolean = false,
    val isSendEmail: Boolean = false,
    val hasEmailError: Boolean = false,
    val errorType: String = ""
)