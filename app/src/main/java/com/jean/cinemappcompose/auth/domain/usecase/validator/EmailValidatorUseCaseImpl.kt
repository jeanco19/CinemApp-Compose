package com.jean.cinemappcompose.auth.domain.usecase.validator

import com.jean.cinemappcompose.auth.domain.model.EmailResult
import com.jean.cinemappcompose.core.domain.util.EmailPatternValidator
import javax.inject.Inject

class EmailValidatorUseCaseImpl @Inject constructor(
    private val emailPatternValidator: EmailPatternValidator
) : EmailValidatorUseCase {

    override fun invoke(email: String): String {
        return if (emailPatternValidator.isValidEmail(email)) {
            EmailResult.VALID.name
        } else {
            EmailResult.INVALID_PATTERN.name
        }
    }

}