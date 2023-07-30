package com.jean.cinemappcompose.profile.data.datasource

import com.jean.cinemappcompose.profile.domain.model.User

interface ProfileRemoteDataSource {

    suspend fun createUser(user: User): Result<Unit>

}