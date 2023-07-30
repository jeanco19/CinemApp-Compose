package com.jean.cinemappcompose.auth.presentation.restart_password.viewmodel

import androidx.annotation.StringRes
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING

data class RestartPasswordUiState(
    val isLoading: Boolean = false,
    val isSendEmail: Boolean = false,
    val isButtonEnable: Boolean = false,
    val email: String = EMPTY_STRING,
    @StringRes val emailError: Int? = null,
    @StringRes val generalError: Int? = null
)