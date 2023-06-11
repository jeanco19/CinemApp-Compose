package com.jean.cinemappcompose.core

import android.util.Patterns

class EmailValidator : EmailPatternValidator {

    override fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}