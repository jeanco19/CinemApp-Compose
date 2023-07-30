package com.jean.cinemappcompose.auth.data.repository

import com.jean.cinemappcompose.auth.data.datasource.AuthRemoteDataSource
import com.jean.cinemappcompose.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override val currentUserID: String get() = authRemoteDataSource.currentUserID

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return authRemoteDataSource.signUp(email, password)
    }

    override suspend fun signIn(email: String, password: String): Result<Unit> {
        return authRemoteDataSource.signIn(email, password)
    }

    override fun signOut() {
        authRemoteDataSource.signOut()
    }

    override suspend fun restartPassword(email: String): Result<Unit> {
        return authRemoteDataSource.restartPassword(email)
    }

}