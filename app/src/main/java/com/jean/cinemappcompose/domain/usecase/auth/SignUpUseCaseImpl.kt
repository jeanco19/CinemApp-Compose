package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.SignUpErrorType
import com.jean.cinemappcompose.domain.model.auth.SignUpResult
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import com.jean.cinemappcompose.domain.utils.EmailPatternValidator
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailPatternValidator: EmailPatternValidator
) : SignUpUseCase {

    override suspend operator fun invoke(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): SignUpResult {
        return if (!emailPatternValidator.isValidEmail(email)) {
            SignUpResult.Error(SignUpErrorType.EMAIL_INVALID_PATTERN)
        } else if (password.length < 8) {
            SignUpResult.Error(SignUpErrorType.PASSWORD_INVALID_LENGTH)
        } else {
            authRepository.signUp(email, password)
        }
    }

}