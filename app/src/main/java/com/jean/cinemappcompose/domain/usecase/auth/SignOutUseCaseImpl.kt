package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.SignOutResult
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignOutUseCase {

    override suspend operator fun invoke(): SignOutResult {
        return authRepository.signOut()
    }

}