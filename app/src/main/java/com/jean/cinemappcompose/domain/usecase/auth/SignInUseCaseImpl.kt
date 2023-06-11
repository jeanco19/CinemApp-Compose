package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.core.Constants.MIN_PASSWORD_LENGTH
import com.jean.cinemappcompose.domain.model.auth.SignInErrorType
import com.jean.cinemappcompose.domain.model.auth.SignInResult
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import com.jean.cinemappcompose.core.EmailPatternValidator
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailPatternValidator: EmailPatternValidator
) : SignInUseCase {

    override suspend operator fun invoke(email: String, password: String): SignInResult {
        return if (!emailPatternValidator.isValidEmail(email.trim())) {
            SignInResult.Error(SignInErrorType.EMAIL_INVALID_PATTERN.name)
        } else if (password.trim().length < MIN_PASSWORD_LENGTH) {
            SignInResult.Error(SignInErrorType.PASSWORD_INVALID_LENGTH.name)
        } else {
            authRepository.signIn(email.trim(), password.trim())
        }
    }

}