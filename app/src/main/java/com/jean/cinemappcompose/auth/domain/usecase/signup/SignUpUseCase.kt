package com.jean.cinemappcompose.auth.domain.usecase.signup

interface SignUpUseCase {

    suspend operator fun invoke(email: String, password: String): Result<Unit>

}