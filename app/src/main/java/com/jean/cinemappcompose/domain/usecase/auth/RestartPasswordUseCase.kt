package com.jean.cinemappcompose.domain.usecase.auth

import com.jean.cinemappcompose.domain.model.auth.RestartPasswordResult

interface RestartPasswordUseCase {

    suspend operator fun invoke(email: String): RestartPasswordResult

}