package com.jean.cinemappcompose.auth.util

import androidx.annotation.StringRes
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.auth.domain.model.RestartPasswordResult
import com.jean.cinemappcompose.auth.domain.model.SignInResult
import com.jean.cinemappcompose.auth.domain.model.SignUpResult
import com.jean.cinemappcompose.auth.domain.model.EmailResult
import com.jean.cinemappcompose.auth.domain.model.PasswordResult

object AuthErrorParser {

    @StringRes
    fun signUpError(error: String): Int {
        return when (error) {
            SignUpResult.SIGN_UP_ERROR.name -> R.string.error_sign_up
            SignUpResult.EMAIL_ALREADY_IN_USE.name -> R.string.error_email_already_in_use
            else -> R.string.generic_error
        }
    }

    @StringRes
    fun signInError(error: String): Int {
        return when (error) {
            SignInResult.SIGN_IN_ERROR.name -> R.string.error_sign_in
            SignInResult.USER_NOT_FOUND.name -> R.string.error_user_not_found
            SignInResult.PASSWORD_WRONG.name -> R.string.error_password_wrong
            else -> R.string.generic_error
        }
    }

    @StringRes
    fun restartPasswordError(error: String): Int {
        return when (error) {
            RestartPasswordResult.RESTART_PASSWORD_ERROR.name -> R.string.error_restart_password
            RestartPasswordResult.USER_NOT_FOUND.name -> R.string.error_user_not_found
            else -> R.string.generic_error
        }
    }

    @StringRes
    fun emailError(error: String): Int {
        return when (error) {
            EmailResult.INVALID_PATTERN.name -> R.string.error_email_invalid_pattern
            else -> R.string.generic_error
        }
    }

    @StringRes
    fun passwordError(error: String): Int {
        return when (error) {
            PasswordResult.INVALID_LENGTH.name -> R.string.error_password_invalid_length
            else -> R.string.generic_error
        }
    }

}