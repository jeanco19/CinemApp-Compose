package com.jean.cinemappcompose.domain.repository.auth

import com.jean.cinemappcompose.domain.model.auth.*

interface AuthRepository {

    suspend fun signUp(email: String, password: String): SignUpResult

    suspend fun signIn(email: String, password: String): SignInResult

    suspend fun signOut(): SignOutResult

    suspend fun createUser(fullName: String, email: String): UserResult

    suspend fun recoverPassword(email: String): Boolean

}