package com.jean.cinemappcompose.presentation.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.Constants.EMPTY_STRING
import com.jean.cinemappcompose.domain.model.auth.*
import com.jean.cinemappcompose.domain.usecase.auth.GetCurrentUserIdUseCase
import com.jean.cinemappcompose.domain.usecase.profile.CreateUserUseCase
import com.jean.cinemappcompose.domain.usecase.auth.SignUpUseCase
import com.jean.cinemappcompose.presentation.auth.uistate.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    var name by mutableStateOf(EMPTY_STRING)
    var lastName by mutableStateOf(EMPTY_STRING)
    var email by mutableStateOf(EMPTY_STRING)
    var password by mutableStateOf(EMPTY_STRING)

    var isButtonEnable = false

    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun doSignUp() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            when (val result = signUpUseCase(email, password)) {
                is SignUpResult.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSignedUp = result.isGranted
                    )
                    createUser()
                }
                is SignUpResult.Error -> {
                    uiState = uiState.copy(isLoading = false)
                    handleResultErrors(result.error)
                }
            }
        }
    }

    private fun createUser() {
        viewModelScope.launch {
            createUserUseCase(getCurrentUserIdUseCase.currentUserID, name, lastName, email)
        }
    }

    private fun handleResultErrors(errorType: String) {
        uiState = uiState.copy(
            hasEmailError = errorType == SignUpErrorType.EMAIL_INVALID_PATTERN.name ||
                            errorType == SignUpErrorType.EMAIL_ALREADY_IN_USE.name,
            hasPasswordError = errorType == SignUpErrorType.PASSWORD_INVALID_LENGTH.name,
            errorType = errorType
        )
    }

    fun handleButtonEnable() {
        isButtonEnable = name.isNotEmpty() && lastName.isNotEmpty() &&
                         email.isNotEmpty() && password.isNotEmpty()
    }

    fun resetFieldErrorMessages() {
        uiState = uiState.copy(
            hasEmailError = false,
            hasPasswordError = false,
            errorType = EMPTY_STRING
        )
    }

    fun resetFieldSignedUp() {
        uiState = uiState.copy(isSignedUp = false)
    }

}