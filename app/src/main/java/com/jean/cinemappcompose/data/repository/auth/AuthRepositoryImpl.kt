package com.jean.cinemappcompose.data.repository.auth

import com.jean.cinemappcompose.data.datasource.remote.auth.AuthRemoteDataSource
import com.jean.cinemappcompose.domain.model.auth.*
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun signUp(email: String, password: String): SignUpResult {
        return authRemoteDataSource.signUp(email, password)
    }

    override suspend fun signIn(email: String, password: String): SignInResult {
        return authRemoteDataSource.signIn(email, password)
    }

    override suspend fun signOut(): SignOutResult {
        return authRemoteDataSource.signOut()
    }

    override suspend fun createUser(fullName: String, email: String): UserResult {
        return authRemoteDataSource.createUser(fullName, email)
    }

    override suspend fun recoverPassword(email: String): Boolean {
        return false
        // TODO implement Firebase recover password method
    }

}