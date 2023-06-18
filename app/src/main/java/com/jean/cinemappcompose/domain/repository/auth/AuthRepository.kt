package com.jean.cinemappcompose.domain.repository.auth

import com.jean.cinemappcompose.domain.model.auth.*

interface AuthRepository {

    val currentUserID: String

    suspend fun signUp(email: String, password: String): SignUpResult

    suspend fun signIn(email: String, password: String): SignInResult

    fun signOut()

    suspend fun restartPassword(email: String): RestartPasswordResult

}