package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.SignUpResult

interface SignUpUseCase {

    suspend operator fun invoke(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): SignUpResult

}