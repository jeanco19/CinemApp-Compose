package com.jean.cinemappcompose.data.mapper

import com.google.firebase.auth.FirebaseAuthException
import com.jean.cinemappcompose.domain.model.auth.RestartPasswordErrorType
import com.jean.cinemappcompose.domain.model.auth.SignInErrorType
import com.jean.cinemappcompose.domain.model.auth.SignUpErrorType

fun FirebaseAuthException.toSignUpErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_EMAIL_ALREADY_IN_USE" -> SignUpErrorType.EMAIL_ALREADY_IN_USE.name
        else -> SignUpErrorType.SIGN_UP_ERROR.name
    }
}

fun FirebaseAuthException.toSignInErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_USER_NOT_FOUND" -> SignInErrorType.USER_NOT_FOUND.name
        "ERROR_WRONG_PASSWORD" -> SignInErrorType.PASSWORD_WRONG.name
        else -> SignInErrorType.SIGN_IN_ERROR.name
    }
}

fun FirebaseAuthException.toRestartPasswordErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_USER_NOT_FOUND" -> RestartPasswordErrorType.USER_NOT_FOUND.name
        else -> RestartPasswordErrorType.RESTART_PASSWORD_ERROR.name
    }
}