package com.jean.cinemappcompose.presentation.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.Constants.EMPTY_STRING
import com.jean.cinemappcompose.domain.model.auth.RestartPasswordErrorType
import com.jean.cinemappcompose.domain.model.auth.RestartPasswordResult
import com.jean.cinemappcompose.domain.usecase.auth.RestartPasswordUseCase
import com.jean.cinemappcompose.presentation.auth.uistate.RestartPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestartPasswordViewModel @Inject constructor(
    private val restartPasswordUseCase: RestartPasswordUseCase
) : ViewModel() {

    var email by mutableStateOf(EMPTY_STRING)

    var uiState by mutableStateOf(RestartPasswordUiState())
        private set

    fun doRestartPassword() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            when (val result = restartPasswordUseCase(email)) {
                is RestartPasswordResult.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSendEmail = result.isSuccess
                    )
                }
                is RestartPasswordResult.Error -> {
                    uiState = uiState.copy(isLoading = false)
                    handleResultErrors(result.error)
                }
            }
        }
    }

    private fun handleResultErrors(errorType: String) {
        uiState = uiState.copy(
            hasEmailError = errorType == RestartPasswordErrorType.EMAIL_INVALID_PATTERN.name,
            errorType = errorType
        )
    }

    fun resetFieldErrorMessages() {
        uiState = uiState.copy(
            hasEmailError = false,
            errorType = EMPTY_STRING
        )
    }

    fun resetSendEmailValue() {
        uiState = uiState.copy(isSendEmail = false)
    }

}