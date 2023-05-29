package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.SignUpResult

interface SignUpUseCase {

    suspend operator fun invoke(email: String, password: String): SignUpResult

}