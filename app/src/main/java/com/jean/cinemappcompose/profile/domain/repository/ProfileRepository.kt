package com.jean.cinemappcompose.profile.domain.repository

import com.jean.cinemappcompose.profile.domain.model.User

interface ProfileRepository {

    suspend fun createUser(user: User): Result<Unit>

}