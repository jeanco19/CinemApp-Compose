package com.jean.cinemappcompose.profile.domain.usecase

import com.jean.cinemappcompose.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserIdUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetCurrentUserIdUseCase {

    override val currentUserID: String get() = authRepository.currentUserID

}