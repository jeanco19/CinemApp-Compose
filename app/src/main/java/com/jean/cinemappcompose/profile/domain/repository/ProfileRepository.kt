package com.jean.cinemappcompose.profile.domain.repository

import com.jean.cinemappcompose.profile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun createUser(user: User)

    fun getUser(id: String): Flow<Result<User>>

}