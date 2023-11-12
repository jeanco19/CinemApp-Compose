package com.jean.cinemappcompose.profile.domain.usecase

import com.jean.cinemappcompose.core.util.userData
import com.jean.cinemappcompose.profile.domain.model.User
import com.jean.cinemappcompose.profile.domain.usecase.GetUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetUserUseCase : GetUserUseCase {
    override fun invoke(): Flow<Result<User>> {
        return flow { emit(Result.success(userData)) }
    }

}