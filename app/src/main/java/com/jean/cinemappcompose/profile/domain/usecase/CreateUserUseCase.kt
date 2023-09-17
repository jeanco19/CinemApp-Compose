package com.jean.cinemappcompose.profile.domain.usecase

interface CreateUserUseCase {

    suspend operator fun invoke(id: String, name: String, lastName: String, email: String)

}