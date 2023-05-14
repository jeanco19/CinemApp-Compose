package com.jean.cinemappcompose.data.util

import android.util.Patterns
import com.jean.cinemappcompose.domain.utils.EmailPatternValidator

class EmailValidator : EmailPatternValidator {

    override fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}