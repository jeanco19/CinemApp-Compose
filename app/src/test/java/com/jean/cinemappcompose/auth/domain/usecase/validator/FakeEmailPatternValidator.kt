package com.jean.cinemappcompose.auth.domain.usecase.validator

import com.jean.cinemappcompose.core.domain.util.EmailPatternValidator

class FakeEmailPatternValidator : EmailPatternValidator  {

    override fun isValidEmail(email: String): Boolean {
        return email.contains(
            regex = Regex(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
            )
        )
    }

}