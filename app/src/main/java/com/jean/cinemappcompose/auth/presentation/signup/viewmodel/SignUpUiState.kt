package com.jean.cinemappcompose.auth.presentation.signup.viewmodel

import androidx.annotation.StringRes
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING

data class SignUpUiState(
    val isLoading: Boolean = false,
    val isSignedUp: Boolean = false,
    val isButtonEnable: Boolean = false,
    val name: String = EMPTY_STRING,
    val lastName: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    @StringRes val generalError: Int? = null,
    val hasConnectivity: Boolean = true,
    @StringRes val connectivityMessage: Int? = null
)