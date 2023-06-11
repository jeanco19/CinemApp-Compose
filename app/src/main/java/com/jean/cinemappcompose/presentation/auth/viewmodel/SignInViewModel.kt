package com.jean.cinemappcompose.presentation.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.Constants.EMPTY_STRING
import com.jean.cinemappcompose.domain.model.auth.SignInErrorType
import com.jean.cinemappcompose.domain.model.auth.SignInResult
import com.jean.cinemappcompose.domain.usecase.auth.GetCurrentUserIdUseCase
import com.jean.cinemappcompose.domain.usecase.auth.SignInUseCase
import com.jean.cinemappcompose.presentation.auth.uistate.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    getCurrentUserIdUseCase: GetCurrentUserIdUseCase
) : ViewModel() {

    var email by mutableStateOf(EMPTY_STRING)
    var password by mutableStateOf(EMPTY_STRING)

    var isButtonEnable = false

    private val currentUserId = getCurrentUserIdUseCase.currentUserID

    var uiState by mutableStateOf(SignInUiState())
        private set

    fun validateSession() {
        if (currentUserId.isNotEmpty()) {
            uiState = uiState.copy(isSignedIn = true)
        }
    }

    fun doSignIn() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            when (val result = signInUseCase(email, password)) {
                is SignInResult.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSignedIn = result.isGranted
                    )
                }
                is SignInResult.Error -> {
                    uiState = uiState.copy(isLoading = false)
                    handleResultErrors(result.error)
                }
            }
        }
    }

    private fun handleResultErrors(errorType: String) {
        uiState = uiState.copy(
            hasEmailError = errorType == SignInErrorType.EMAIL_INVALID_PATTERN.name ||
                            errorType == SignInErrorType.USER_NOT_FOUND.name,
            hasPasswordError = errorType == SignInErrorType.PASSWORD_INVALID_LENGTH.name ||
                               errorType == SignInErrorType.PASSWORD_WRONG.name,
            errorType = errorType
        )
    }

    fun handleButtonEnable() {
        isButtonEnable = email.isNotEmpty() && password.isNotEmpty()
    }

    fun resetFieldErrorMessages() {
        uiState = uiState.copy(
            hasEmailError = false,
            hasPasswordError = false,
            errorType = EMPTY_STRING
        )
    }

}