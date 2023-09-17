package com.jean.cinemappcompose.profile.data.datasource

import com.jean.cinemappcompose.profile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ProfileRemoteDataSource {

    suspend fun createUser(user: User)

    fun getUser(id: String): Flow<User>

}