package com.jean.cinemappcompose.auth.domain.usecase.validator

interface PasswordValidatorUseCase {

    operator fun invoke(password: String): String

}