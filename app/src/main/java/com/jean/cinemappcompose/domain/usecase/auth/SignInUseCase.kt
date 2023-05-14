package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.SignInResult

interface SignInUseCase {

    suspend operator fun invoke(email: String, password: String): SignInResult

}