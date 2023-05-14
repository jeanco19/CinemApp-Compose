package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.SignOutResult

interface SignOutUseCase {

    suspend operator fun invoke(): SignOutResult

}