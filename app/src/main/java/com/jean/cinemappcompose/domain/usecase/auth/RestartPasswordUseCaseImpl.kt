package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.core.EmailPatternValidator
import com.jean.cinemappcompose.domain.model.auth.RestartPasswordErrorType
import com.jean.cinemappcompose.domain.model.auth.RestartPasswordResult
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import javax.inject.Inject

class RestartPasswordUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailPatternValidator: EmailPatternValidator
) : RestartPasswordUseCase {

    override suspend fun invoke(email: String): RestartPasswordResult {
        return if (!emailPatternValidator.isValidEmail(email.trim())) {
            RestartPasswordResult.Error(RestartPasswordErrorType.EMAIL_INVALID_PATTERN.name)
        } else {
            authRepository.restartPassword(email)
        }
    }

}