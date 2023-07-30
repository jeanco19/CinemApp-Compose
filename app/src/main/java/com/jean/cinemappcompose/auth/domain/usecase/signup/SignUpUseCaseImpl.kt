package com.jean.cinemappcompose.auth.domain.usecase.signup

import com.jean.cinemappcompose.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignUpUseCase {

    override suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return authRepository.signUp(email.trim(), password.trim())
    }

}