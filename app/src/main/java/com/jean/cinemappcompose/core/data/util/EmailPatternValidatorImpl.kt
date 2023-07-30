package com.jean.cinemappcompose.core.data.util

import android.util.Patterns
import com.jean.cinemappcompose.core.domain.util.EmailPatternValidator

class EmailPatternValidatorImpl : EmailPatternValidator {

    override fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}