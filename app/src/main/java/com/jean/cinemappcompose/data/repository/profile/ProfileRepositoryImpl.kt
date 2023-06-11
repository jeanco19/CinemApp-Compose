package com.jean.cinemappcompose.data.repository.profile

import com.jean.cinemappcompose.data.datasource.remote.profile.ProfileRemoteDataSource
import com.jean.cinemappcompose.domain.model.profile.ProfileResult
import com.jean.cinemappcompose.domain.model.profile.User
import com.jean.cinemappcompose.domain.repository.profile.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun createUser(user: User): ProfileResult {
        return profileRemoteDataSource.createUser(user)
    }

}