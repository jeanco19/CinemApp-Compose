package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import javax.inject.Inject

class GetCurrentUserIdUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetCurrentUserIdUseCase {

    override val currentUserID: String get() = authRepository.currentUserID

}