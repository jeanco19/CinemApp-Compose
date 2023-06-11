package com.jean.cinemappcompose.domain.model.auth

sealed class SignUpResult {
    data class Error(val error: String) : SignUpResult()
    data class Success(val isGranted: Boolean = false) : SignUpResult()
}

enum class SignUpErrorType {
    SIGN_UP_ERROR,
    EMAIL_INVALID_PATTERN,
    EMAIL_ALREADY_IN_USE,
    PASSWORD_INVALID_LENGTH
}

sealed class SignInResult {
    data class Error(val error: String) : SignInResult()
    data class Success(val isGranted: Boolean = false) : SignInResult()
}

enum class SignInErrorType {
    SIGN_IN_ERROR,
    EMAIL_INVALID_PATTERN,
    USER_NOT_FOUND,
    PASSWORD_INVALID_LENGTH,
    PASSWORD_WRONG
}