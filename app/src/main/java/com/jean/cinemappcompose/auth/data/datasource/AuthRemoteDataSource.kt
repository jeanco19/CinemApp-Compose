package com.jean.cinemappcompose.auth.data.datasource

interface AuthRemoteDataSource {

    val currentUserID: String

    suspend fun signUp(email: String, password: String): Result<Unit>

    suspend fun signIn(email: String, password: String): Result<Unit>

    fun signOut()

    suspend fun restartPassword(email: String): Result<Unit>

}