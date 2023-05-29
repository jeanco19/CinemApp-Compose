package com.jean.cinemappcompose.presentation.auth.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.domain.model.auth.*
import com.jean.cinemappcompose.domain.usecase.auth.CreateUserUseCase
import com.jean.cinemappcompose.domain.usecase.auth.SignUpUseCase
import com.jean.cinemappcompose.presentation.auth.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    var name: MutableState<String> = mutableStateOf("")
    var lastName: MutableState<String> = mutableStateOf("")
    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")

    var isButtonEnable = false

    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun doSignUp() {
        viewModelScope.launch {
            when (val result =
                signUpUseCase(email.value, password.value)) {
                is SignUpResult.Success -> {
                    uiState = uiState.copy(isLoading = true)
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
            uiState = when (createUserUseCase(name.value, lastName.value, email.value)) {
                is UserResult.Success -> {
                    uiState.copy(isLoading = false, isSignedUp = true)
                }
                is UserResult.Error -> {
                    uiState.copy(isLoading = false)
                }
            }
        }
    }

    private fun handleResultErrors(errorType: SignUpErrorType) {
        uiState = when (errorType) {
            SignUpErrorType.SIGN_UP_ERROR -> {
                uiState.copy(
                    isSignedUp = false,
                    errorType = SignUpUiState.SignUpUiErrors.SIGN_UP_ERROR.name
                )
            }
            SignUpErrorType.EMAIL_INVALID_PATTERN -> {
                uiState.copy(
                    hasEmailError = true,
                    errorType = SignUpUiState.SignUpUiErrors.EMAIL_INVALID_PATTERN.name
                )
            }
            SignUpErrorType.PASSWORD_INVALID_LENGTH -> {
                uiState.copy(
                    hasPasswordError = true,
                    errorType = SignUpUiState.SignUpUiErrors.PASSWORD_INVALID_LENGTH.name
                )
            }
        }
    }

    fun handleButtonEnable() {
        isButtonEnable = name.value.isNotEmpty() &&
                         lastName.value.isNotEmpty() &&
                         email.value.isNotEmpty() &&
                         password.value.isNotEmpty()
    }

    fun resetFieldErrorMessages() {
        uiState = uiState.copy(
            hasEmailError = false,
            hasPasswordError = false
        )
    }

    fun resetFieldSignedUp() {
        uiState = uiState.copy(isSignedUp = false)
    }

}