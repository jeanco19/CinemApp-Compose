package com.jean.cinemappcompose.profile.data.repository

import com.jean.cinemappcompose.profile.data.datasource.ProfileRemoteDataSource
import com.jean.cinemappcompose.profile.domain.model.User
import com.jean.cinemappcompose.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun createUser(user: User) {
        profileRemoteDataSource.createUser(user)
    }

    override fun getUser(id: String): Flow<Result<User>> {
        return flow {
            try {
                profileRemoteDataSource.getUser(id).collect { user ->
                    emit(Result.success(user))
                }
            } catch (exception: Exception) {
                emit(Result.failure(Throwable(exception.message)))
            }
        }
    }

}