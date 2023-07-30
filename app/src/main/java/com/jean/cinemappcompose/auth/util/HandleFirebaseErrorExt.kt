package com.jean.cinemappcompose.auth.util

import com.google.firebase.auth.FirebaseAuthException
import com.jean.cinemappcompose.auth.domain.model.*

fun FirebaseAuthException.toSignUpErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_EMAIL_ALREADY_IN_USE" -> SignUpResult.EMAIL_ALREADY_IN_USE.name
        else -> SignUpResult.SIGN_UP_ERROR.name
    }
}

fun FirebaseAuthException.toSignInErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_USER_NOT_FOUND" -> SignInResult.USER_NOT_FOUND.name
        "ERROR_WRONG_PASSWORD" -> SignInResult.PASSWORD_WRONG.name
        else -> SignInResult.SIGN_IN_ERROR.name
    }
}

fun FirebaseAuthException.toRestartPasswordErrorTypes(exception: String): String {
    return when (exception) {
        "ERROR_USER_NOT_FOUND" -> RestartPasswordResult.USER_NOT_FOUND.name
        else -> RestartPasswordResult.RESTART_PASSWORD_ERROR.name
    }
}