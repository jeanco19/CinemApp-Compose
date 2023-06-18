package com.jean.cinemappcompose.data.repository.auth

import com.jean.cinemappcompose.data.datasource.remote.auth.AuthRemoteDataSource
import com.jean.cinemappcompose.domain.model.auth.*
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override val currentUserID: String get() = authRemoteDataSource.currentUserID

    override suspend fun signUp(email: String, password: String): SignUpResult {
        return authRemoteDataSource.signUp(email, password)
    }

    override suspend fun signIn(email: String, password: String): SignInResult {
        return authRemoteDataSource.signIn(email, password)
    }

    override fun signOut() {
        authRemoteDataSource.signOut()
    }

    override suspend fun restartPassword(email: String): RestartPasswordResult {
        return authRemoteDataSource.restartPassword(email)
    }

}