package com.jean.cinemappcompose.profile.domain.usecase

import com.jean.cinemappcompose.profile.domain.model.User
import com.jean.cinemappcompose.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase
) : GetUserUseCase {

    override fun invoke(): Flow<Result<User>> {
        return profileRepository.getUser(getCurrentUserIdUseCase.currentUserID)
    }

}