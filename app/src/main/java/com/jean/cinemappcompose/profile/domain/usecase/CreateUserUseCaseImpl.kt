package com.jean.cinemappcompose.profile.domain.usecase

import com.jean.cinemappcompose.core.util.Constants.SPACE_STRING
import com.jean.cinemappcompose.profile.domain.model.User
import com.jean.cinemappcompose.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
) : CreateUserUseCase {

    override suspend operator fun invoke(
        id: String,
        name: String,
        lastName: String,
        email: String
    ): Result<Unit> {
        val fullName = StringBuilder()
            .append(name)
            .append(SPACE_STRING)
            .append(lastName).toString()
        return profileRepository.createUser(User(id = id, fullName = fullName, email = email))
    }

}