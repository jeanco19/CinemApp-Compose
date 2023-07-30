package com.jean.cinemappcompose.auth.presentation.signin.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.auth.domain.usecase.signin.SignInUseCase
import com.jean.cinemappcompose.auth.util.AuthErrorParser
import com.jean.cinemappcompose.auth.domain.model.EmailResult
import com.jean.cinemappcompose.auth.domain.model.PasswordResult
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.PasswordValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val passwordValidatorUseCase: PasswordValidatorUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SignInUiState())
        private set

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChange -> {
                uiState = uiState.copy(
                    email = event.email,
                    emailError = null
                )
            }
            is SignInEvent.PasswordChange -> {
                uiState = uiState.copy(
                    password = event.password,
                    passwordError = null
                )
            }
            is SignInEvent.SignIn -> doSignIn()
        }
        enableButton()
    }

    private fun doSignIn() {
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch {
            val emailResult = emailValidatorUseCase(uiState.email)
            val passwordResult = passwordValidatorUseCase(uiState.password)
            if (emailResult == EmailResult.VALID.name
                && passwordResult == PasswordResult.VALID.name) {
                signInUseCase(uiState.email, uiState.password)
                    .onSuccess {
                        uiState = uiState.copy(isSignedIn = true)
                    }
                    .onFailure {throwable ->
                        uiState = uiState.copy(
                            generalError = AuthErrorParser.signInError(
                                throwable.message ?: EMPTY_STRING
                            )
                        )
                    }
            } else {
                if (emailResult != EmailResult.VALID.name)
                    uiState = uiState.copy(emailError = AuthErrorParser.emailError(emailResult))
                if (passwordResult != PasswordResult.VALID.name)
                    uiState = uiState.copy(passwordError = AuthErrorParser.passwordError(passwordResult))
            }
        }
        uiState = uiState.copy(
            isLoading = false,
            isSignedIn = false,
            generalError = null
        )
    }

    private fun enableButton() {
        uiState = uiState.copy(
            isButtonEnable = uiState.email.isNotEmpty() && uiState.password.isNotEmpty()
        )
    }

}