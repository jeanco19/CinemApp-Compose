package com.jean.cinemappcompose.auth.domain.usecase.signin

import com.jean.cinemappcompose.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignInUseCase {

    override suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return authRepository.signIn(email.trim(), password.trim())
    }

}