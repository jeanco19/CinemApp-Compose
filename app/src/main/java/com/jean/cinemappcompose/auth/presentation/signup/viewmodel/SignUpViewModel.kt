package com.jean.cinemappcompose.auth.presentation.signup.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.profile.domain.usecase.GetCurrentUserIdUseCase
import com.jean.cinemappcompose.profile.domain.usecase.CreateUserUseCase
import com.jean.cinemappcompose.auth.domain.usecase.signup.SignUpUseCase
import com.jean.cinemappcompose.auth.util.AuthErrorParser
import com.jean.cinemappcompose.auth.domain.model.EmailResult
import com.jean.cinemappcompose.auth.domain.model.PasswordResult
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.PasswordValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val passwordValidatorUseCase: PasswordValidatorUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.NameChange -> {
                uiState = uiState.copy(name = event.name)
            }
            is SignUpEvent.LastName -> {
                uiState = uiState.copy(lastName = event.lastName)
            }
            is SignUpEvent.EmailChange -> {
                uiState = uiState.copy(
                    email = event.email,
                    emailError = null
                )
            }
            is SignUpEvent.PasswordChange -> {
                uiState = uiState.copy(
                    password = event.password,
                    passwordError = null
                )
            }
            is SignUpEvent.SignUp -> doSignUp()
        }
        validateButton()
    }

    private fun doSignUp() {
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch {
            val emailResult = emailValidatorUseCase(uiState.email)
            val passwordResult = passwordValidatorUseCase(uiState.password)
            if (emailResult == EmailResult.VALID.name &&
                passwordResult == PasswordResult.VALID.name) {
                signUpUseCase(uiState.email, uiState.password)
                    .onSuccess {
                        uiState = uiState.copy(isSignedUp = true)
                        createUserUseCase(
                            id = getCurrentUserIdUseCase.currentUserID,
                            name = uiState.name,
                            lastName = uiState.lastName,
                            email = uiState.email
                        )
                    }
                    .onFailure { throwable ->
                        uiState = uiState.copy(
                            generalError = AuthErrorParser.signUpError(
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
            isSignedUp = false,
            generalError = null
        )
    }

    private fun validateButton() {
        uiState = uiState.copy(
            isButtonEnable = uiState.name.isNotEmpty() &&
                    uiState.lastName.isNotEmpty() &&
                    uiState.email.isNotEmpty() &&
                    uiState.password.isNotEmpty()
        )
    }

}