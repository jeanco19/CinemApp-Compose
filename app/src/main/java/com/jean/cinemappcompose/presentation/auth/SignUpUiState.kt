package com.jean.cinemappcompose.presentation.auth

data class SignUpUiState(
    val isLoading: Boolean = false,
    val isSignedUp: Boolean = false,
    val hasEmailError: Boolean = false,
    val hasPasswordError: Boolean = false,
    val errorType: String = ""
) {

    enum class SignUpUiErrors {
        SIGN_UP_ERROR,
        EMAIL_INVALID_PATTERN,
        PASSWORD_INVALID_LENGTH
    }

}