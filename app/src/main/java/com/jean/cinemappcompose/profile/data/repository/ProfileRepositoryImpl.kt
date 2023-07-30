package com.jean.cinemappcompose.profile.data.repository

import com.jean.cinemappcompose.profile.data.datasource.ProfileRemoteDataSource
import com.jean.cinemappcompose.profile.domain.model.User
import com.jean.cinemappcompose.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun createUser(user: User): Result<Unit> {
        return profileRemoteDataSource.createUser(user)
    }

}