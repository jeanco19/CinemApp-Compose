package com.jean.cinemappcompose.domain.usecase.profile

import com.jean.cinemappcompose.domain.model.profile.ProfileResult

interface CreateUserUseCase {

    suspend operator fun invoke(id: String, name: String, lastName: String, email: String): ProfileResult

}