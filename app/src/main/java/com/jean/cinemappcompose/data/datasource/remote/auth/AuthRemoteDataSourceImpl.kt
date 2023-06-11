package com.jean.cinemappcompose.data.datasource.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.jean.cinemappcompose.core.Constants.EMPTY_STRING
import com.jean.cinemappcompose.data.mapper.toSignInErrorTypes
import com.jean.cinemappcompose.data.mapper.toSignUpErrorTypes
import com.jean.cinemappcompose.domain.model.auth.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthRemoteDataSource {

    override val currentUserID: String get() = firebaseAuth.uid ?: EMPTY_STRING

    override suspend fun signUp(email: String, password: String): SignUpResult =
        withContext(ioDispatcher) {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                SignUpResult.Success(isGranted = true)
            } catch (exception: FirebaseAuthException) {
                SignUpResult.Error(exception.toSignUpErrorTypes(exception.errorCode))
            }
        }

    override suspend fun signIn(email: String, password: String): SignInResult {
        return withContext(ioDispatcher) {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                SignInResult.Success(isGranted = true)
            } catch (exception: FirebaseAuthException) {
                SignInResult.Error(exception.toSignInErrorTypes(exception.errorCode))
            }
        }
    }


    override fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun recoverPassword(email: String): Boolean {
        return false
    }

}