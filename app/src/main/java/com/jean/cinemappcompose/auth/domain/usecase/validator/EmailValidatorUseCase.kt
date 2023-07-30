package com.jean.cinemappcompose.auth.domain.usecase.validator

interface EmailValidatorUseCase {

    operator fun invoke(email: String): String

}