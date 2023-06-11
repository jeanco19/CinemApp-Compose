package com.jean.cinemappcompose.data.datasource.remote.profile

import com.jean.cinemappcompose.domain.model.profile.ProfileResult
import com.jean.cinemappcompose.domain.model.profile.User

interface ProfileRemoteDataSource {

    suspend fun createUser(user: User): ProfileResult

}