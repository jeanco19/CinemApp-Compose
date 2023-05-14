package com.jean.cinemappcompose.presentation.auth.viewmodel

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

    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun doSignUp(
        name: String,
        lastName: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            when (val result =
                signUpUseCase(name.trim(), lastName.trim(), email.trim(), password.trim())) {
                is SignUpResult.Success -> {
                    uiState = uiState.copy(isLoading = true)
                    createUser(name, lastName, email)
                }
                is SignUpResult.Error -> {
                    uiState = uiState.copy(isLoading = false)
                    handleResultErrors(result.error)
                }
            }
        }
    }

    private fun createUser(name: String, lastName: String, email: String) {
        viewModelScope.launch {
            uiState = when (createUserUseCase(name.trim(), lastName.trim(), email.trim())) {
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
                    errorMessage = "Ocurrió un error al realizar regístro. Inténtalo de nuevo."
                )
            }
            SignUpErrorType.EMAIL_INVALID_PATTERN -> {
                uiState.copy(
                    hasEmailError = true,
                    errorFieldMessage = "Formato de correo electrónico inválido."
                )
            }
            SignUpErrorType.PASSWORD_INVALID_LENGTH -> {
                uiState.copy(
                    hasPasswordError = true,
                    errorFieldMessage = "Tamaño de contraseña inválido, debe contener 8 dígitos."
                )
            }
        }
    }

    fun handleButtonEnable(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): Boolean {
        return name.isNotEmpty() &&
                lastName.isNotEmpty() &&
                email.isNotEmpty() &&
                password.isNotEmpty()
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