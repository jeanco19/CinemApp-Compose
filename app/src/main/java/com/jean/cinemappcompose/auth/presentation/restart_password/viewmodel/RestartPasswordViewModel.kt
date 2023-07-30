package com.jean.cinemappcompose.auth.presentation.restart_password.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.auth.domain.usecase.restart_password.RestartPasswordUseCase
import com.jean.cinemappcompose.auth.util.AuthErrorParser
import com.jean.cinemappcompose.auth.domain.model.EmailResult
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestartPasswordViewModel @Inject constructor(
    private val restartPasswordUseCase: RestartPasswordUseCase,
    private val emailValidatorUseCase: EmailValidatorUseCase
) : ViewModel() {

    var uiState by mutableStateOf(RestartPasswordUiState())
        private set

    fun onEvent(event: RestartPasswordEvent) {
        when (event) {
            is RestartPasswordEvent.EmailChange -> {
                uiState = uiState.copy(
                    email = event.email,
                    emailError = null,
                    isButtonEnable = event.email.isNotEmpty()
                )
            }
            is RestartPasswordEvent.RestartPassword -> doRestartPassword()
        }
    }

    private fun doRestartPassword() {
        uiState = uiState.copy(isLoading = true, isButtonEnable = false)
        viewModelScope.launch {
            val emailResult = emailValidatorUseCase(uiState.email)
            if (emailResult == EmailResult.VALID.name) {
                restartPasswordUseCase(uiState.email)
                    .onSuccess {
                        uiState = uiState.copy(isSendEmail = true)
                    }
                    .onFailure {throwable ->
                        uiState = uiState.copy(
                            generalError = AuthErrorParser.restartPasswordError(
                                throwable.message ?: EMPTY_STRING
                            )
                        )
                    }
            } else {
                if (emailResult != EmailResult.VALID.name)
                    uiState = uiState.copy(
                        emailError = AuthErrorParser.emailError(emailResult)
                    )
            }
        }
        uiState = uiState.copy(
            isLoading = false,
            isSendEmail = false,
            generalError = null
        )
    }

}