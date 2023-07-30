package com.jean.cinemappcompose.auth.domain.usecase.restart_password

import com.jean.cinemappcompose.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RestartPasswordUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : RestartPasswordUseCase {

    override suspend fun invoke(email: String): Result<Unit> {
        return authRepository.restartPassword(email)
    }

}