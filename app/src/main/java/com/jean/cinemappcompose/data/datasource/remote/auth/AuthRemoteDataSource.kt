package com.jean.cinemappcompose.data.datasource.remote.auth

import com.jean.cinemappcompose.domain.model.auth.*

interface AuthRemoteDataSource {

    val currentUserID: String

    suspend fun signUp(email: String, password: String): SignUpResult

    suspend fun signIn(email: String, password: String): SignInResult

    fun signOut()

    suspend fun restartPassword(email: String): RestartPasswordResult

}