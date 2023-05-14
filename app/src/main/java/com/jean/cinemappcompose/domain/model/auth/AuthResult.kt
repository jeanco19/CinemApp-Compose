package com.jean.cinemappcompose.domain.model.auth

sealed class SignUpResult {
    data class Error(val error: SignUpErrorType) : SignUpResult()
    data class Success(val isGranted: Boolean) : SignUpResult()
}

enum class SignUpErrorType {
    SIGN_UP_ERROR,
    EMAIL_INVALID_PATTERN,
    PASSWORD_INVALID_LENGTH
}

sealed class SignInResult {
    data class Error(val error: SignInErrorType) : SignInResult()
    data class Success(val isGranted: Boolean) : SignInResult()
}

enum class SignInErrorType {
    SIGN_IN_ERROR,
    EMAIL_INVALID_PATTERN,
    PASSWORD_INVALID_LENGTH
}

sealed class UserResult {
    object Error : UserResult()
    class Success(val isRegistered: Boolean = false, val user: User? = null) : UserResult()
}

sealed class SignOutResult {
    data class Error(val error: String = "") : SignOutResult()
    data class Success(val isGranted: Boolean) : SignOutResult()
}