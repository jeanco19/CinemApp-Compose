package com.jean.cinemappcompose.auth.domain.usecase.validator

import com.jean.cinemappcompose.auth.domain.model.PasswordResult
import com.jean.cinemappcompose.core.util.Constants

class PasswordValidatorUseCaseImpl : PasswordValidatorUseCase {

    override fun invoke(password: String): String {
        return if (password.trim().length >= Constants.MIN_PASSWORD_LENGTH) {
            PasswordResult.VALID.name
        } else {
            PasswordResult.INVALID_LENGTH.name
        }
    }

}