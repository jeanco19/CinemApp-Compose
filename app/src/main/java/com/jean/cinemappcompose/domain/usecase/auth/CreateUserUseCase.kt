package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.UserResult

interface CreateUserUseCase {

    suspend operator fun invoke(name: String, lastName: String, email: String): UserResult

}