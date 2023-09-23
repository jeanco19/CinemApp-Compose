package com.jean.cinemappcompose.auth.presentation.signin.viewmodel

import androidx.annotation.StringRes
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.core.util.connectivity.ConnectivityStatus

data class SignInUiState(
    val isLoading: Boolean = false,
    val isSignedIn: Boolean = false,
    val isButtonEnable: Boolean = false,
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    @StringRes val generalError: Int? = null,
    val hasConnectivity: Boolean = true,
    @StringRes val connectivityMessage: Int? = null
)