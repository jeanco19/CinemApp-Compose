package com.jean.cinemappcompose.data.datasource.remote.auth

import com.jean.cinemappcompose.domain.model.auth.*

interface AuthRemoteDataSource {

    suspend fun signUp(email: String, password: String): SignUpResult

    suspend fun signIn(email: String, password: String): SignInResult

    suspend fun signOut(): SignOutResult

    suspend fun createUser(fullName: String, email: String): UserResult

    suspend fun recoverPassword(email: String): Boolean

}