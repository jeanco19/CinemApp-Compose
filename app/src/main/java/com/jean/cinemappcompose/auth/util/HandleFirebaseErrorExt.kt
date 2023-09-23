package com.jean.cinemappcompose.auth.util

import com.google.firebase.auth.FirebaseAuthException
import com.jean.cinemappcompose.auth.domain.model.*

fun FirebaseAuthException.toSignUpErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_EMAIL_ALREADY_IN_USE" -> SignUpErrorResult.EMAIL_ALREADY_IN_USE.name
        else -> SignUpErrorResult.SIGN_UP_ERROR.name
    }
}

fun FirebaseAuthException.toSignInErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_USER_NOT_FOUND" -> SignInErrorResult.USER_NOT_FOUND.name
        "ERROR_WRONG_PASSWORD" -> SignInErrorResult.PASSWORD_WRONG.name
        else -> SignInErrorResult.SIGN_IN_ERROR.name
    }
}

fun FirebaseAuthException.toRestartPasswordErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_USER_NOT_FOUND" -> RestartPasswordErrorResult.USER_NOT_FOUND.name
        else -> RestartPasswordErrorResult.RESTART_PASSWORD_ERROR.name
    }
}