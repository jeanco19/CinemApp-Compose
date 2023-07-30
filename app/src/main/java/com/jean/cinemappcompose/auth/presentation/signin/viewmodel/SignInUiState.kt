package com.jean.cinemappcompose.auth.presentation.signin.viewmodel

import androidx.annotation.StringRes
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING

data class SignInUiState(
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val isButtonEnable: Boolean = false,
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    @StringRes val generalError: Int? = null
)