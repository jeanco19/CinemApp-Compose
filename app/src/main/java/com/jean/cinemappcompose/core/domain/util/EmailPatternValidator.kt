package com.jean.cinemappcompose.core.domain.util

interface EmailPatternValidator {

    fun isValidEmail(email: String): Boolean

}