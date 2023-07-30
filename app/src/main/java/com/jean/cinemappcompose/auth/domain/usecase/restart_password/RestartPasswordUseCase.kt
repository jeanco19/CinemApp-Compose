package com.jean.cinemappcompose.auth.domain.usecase.restart_password

interface RestartPasswordUseCase {

    suspend operator fun invoke(email: String): Result<Unit>

}