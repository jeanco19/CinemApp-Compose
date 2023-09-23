package com.jean.cinemappcompose.auth.util

import androidx.annotation.StringRes
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.auth.domain.model.RestartPasswordErrorResult
import com.jean.cinemappcompose.auth.domain.model.SignInErrorResult
import com.jean.cinemappcompose.auth.domain.model.SignUpErrorResult
import com.jean.cinemappcompose.auth.domain.model.EmailResult
import com.jean.cinemappcompose.auth.domain.model.PasswordResult

object AuthErrorParser {

    @StringRes
    fun signUpError(error: String): Int {
        return when (error) {
            SignUpErrorResult.SIGN_UP_ERROR.name -> R.string.error_sign_up
            SignUpErrorResult.EMAIL_ALREADY_IN_USE.name -> R.string.error_email_already_in_use
            SignUpErrorResult.UNAVAILABLE_NETWORK.name -> R.string.generic_connectivity_error
            else -> R.string.generic_error
        }
    }

    @StringRes
    fun signInError(error: String): Int {
        return when (error) {
            SignInErrorResult.SIGN_IN_ERROR.name -> R.string.error_sign_in
            SignInErrorResult.USER_NOT_FOUND.name -> R.string.error_user_not_found
            SignInErrorResult.PASSWORD_WRONG.name -> R.string.error_password_wrong
            SignInErrorResult.UNAVAILABLE_NETWORK.name -> R.string.generic_connectivity_error
            else -> R.string.generic_error
        }
    }

    @StringRes
    fun restartPasswordError(error: String): Int {
        return when (error) {
            RestartPasswordErrorResult.RESTART_PASSWORD_ERROR.name -> R.string.error_restart_password
            RestartPasswordErrorResult.USER_NOT_FOUND.name -> R.string.error_user_not_found
            RestartPasswordErrorResult.UNAVAILABLE_NETWORK.name -> R.string.generic_connectivity_error
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