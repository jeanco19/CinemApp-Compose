package com.jean.cinemappcompose.domain.utils

interface EmailPatternValidator {

    fun isValidEmail(email: String): Boolean

}