package com.jean.cinemappcompose.auth.data.datasource

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.jean.cinemappcompose.auth.domain.model.RestartPasswordResult
import com.jean.cinemappcompose.auth.domain.model.SignInResult
import com.jean.cinemappcompose.auth.domain.model.SignUpResult
import com.jean.cinemappcompose.auth.util.toRestartPasswordErrorTypes
import com.jean.cinemappcompose.auth.util.toSignInErrorTypes
import com.jean.cinemappcompose.auth.util.toSignUpErrorTypes
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
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

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                Result.success(Unit)
            } catch (exception: FirebaseAuthException) {
                Result.failure(
                    Throwable(message = exception.toSignUpErrorTypes(exception.errorCode))
                )
            } catch (exception: FirebaseNetworkException) {
                Result.failure(Throwable(message = SignUpResult.UNAVAILABLE_NETWORK.name))
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                Result.success(Unit)
            } catch (exception: FirebaseAuthException) {
                Result.failure(
                    Throwable(message = exception.toSignInErrorTypes(exception.errorCode))
                )
            } catch (exception: FirebaseNetworkException) {
                Result.failure(Throwable(message = SignInResult.UNAVAILABLE_NETWORK.name))
            }
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun restartPassword(email: String): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                firebaseAuth.sendPasswordResetEmail(email).await()
                Result.success(Unit)
            } catch (exception: FirebaseAuthException) {
                Result.failure(
                    Throwable(message = exception.toRestartPasswordErrorTypes(exception.errorCode))
                )
            } catch (exception: FirebaseNetworkException) {
                Result.failure(Throwable(message = RestartPasswordResult.UNAVAILABLE_NETWORK.name))
            }
        }
    }

}