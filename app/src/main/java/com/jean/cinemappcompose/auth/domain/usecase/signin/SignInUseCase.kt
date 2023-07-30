package com.jean.cinemappcompose.auth.domain.usecase.signin

interface SignInUseCase {

    suspend operator fun invoke(email: String, password: String): Result<Unit>

}