package com.jean.cinemappcompose.domain.usecase.profile

import com.jean.cinemappcompose.core.Constants.SPACE_STRING
import com.jean.cinemappcompose.domain.model.profile.ProfileResult
import com.jean.cinemappcompose.domain.model.profile.User
import com.jean.cinemappcompose.domain.repository.profile.ProfileRepository
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
) : CreateUserUseCase {

    override suspend operator fun invoke(
        id: String,
        name: String,
        lastName: String,
        email: String
    ): ProfileResult {
        val fullName = StringBuilder()
            .append(name)
            .append(SPACE_STRING)
            .append(lastName).toString()
        return profileRepository.createUser(User(id = id, fullName = fullName, email = email))
    }

}