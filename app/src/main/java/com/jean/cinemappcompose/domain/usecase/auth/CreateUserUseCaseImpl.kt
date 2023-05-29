package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.UserResult
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : CreateUserUseCase {

    override suspend operator fun invoke(
        name: String,
        lastName: String,
        email: String
    ): UserResult {
        val fullName = StringBuilder()
            .append(name)
            .append(" ")
            .append(lastName).toString()
        return authRepository.createUser(fullName.trim(), email.trim())
    }

}