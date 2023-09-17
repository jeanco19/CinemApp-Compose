package com.jean.cinemappcompose.profile.domain.usecase

import com.jean.cinemappcompose.profile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserUseCase {

    fun invoke(): Flow<Result<User>>

}