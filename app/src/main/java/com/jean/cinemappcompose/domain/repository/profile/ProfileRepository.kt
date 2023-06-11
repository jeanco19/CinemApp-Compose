package com.jean.cinemappcompose.domain.repository.profile

import com.jean.cinemappcompose.domain.model.profile.ProfileResult
import com.jean.cinemappcompose.domain.model.profile.User

interface ProfileRepository {

    suspend fun createUser(user: User): ProfileResult

}