package com.jean.cinemappcompose.auth.domain.usecase.signout

import com.jean.cinemappcompose.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignOutUseCase {

    override suspend operator fun invoke() {
        return authRepository.signOut()
    }

}