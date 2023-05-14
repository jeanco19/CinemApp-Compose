package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.SignInErrorType
import com.jean.cinemappcompose.domain.model.auth.SignInResult
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import com.jean.cinemappcompose.domain.utils.EmailPatternValidator
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailPatternValidator: EmailPatternValidator
) : SignInUseCase {

    override suspend operator fun invoke(email: String, password: String): SignInResult {
        return if (emailPatternValidator.isValidEmail(email)) {
            SignInResult.Error(SignInErrorType.EMAIL_INVALID_PATTERN)
        } else if (password.length < 8) {
            SignInResult.Error(SignInErrorType.PASSWORD_INVALID_LENGTH)
        } else {
            authRepository.signIn(email, password)
        }
    }

}