package com.jean.cinemappcompose.data.datasource.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.jean.cinemappcompose.data.util.Constants.COLLECTION_USERS
import com.jean.cinemappcompose.data.util.Constants.EMAIL_FIELD
import com.jean.cinemappcompose.data.util.Constants.FULL_NAME_FIELD
import com.jean.cinemappcompose.domain.model.auth.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthRemoteDataSource {

    override suspend fun signUp(email: String, password: String): SignUpResult =
        withContext(ioDispatcher) {
            try {
                var isSignUpGranted = false
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { isSignUpGranted = true }
                    .addOnFailureListener { isSignUpGranted = false }
                SignUpResult.Success(isGranted = isSignUpGranted)
            } catch (exception: FirebaseAuthException) {
                SignUpResult.Error(SignUpErrorType.SIGN_UP_ERROR)
            }
        }

    override suspend fun signIn(email: String, password: String): SignInResult =
        withContext(ioDispatcher) {
            try {
                var isLogInGranted = false
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener { isLogInGranted = true }
                    .addOnFailureListener { isLogInGranted = false }
                SignInResult.Success(isGranted = isLogInGranted)
            } catch (exception: FirebaseAuthException) {
                SignInResult.Error(SignInErrorType.SIGN_IN_ERROR)
            }
        }


    override suspend fun signOut(): SignOutResult =
        withContext(ioDispatcher) {
        try {
            firebaseAuth.signOut()
            SignOutResult.Success(isGranted = true)
        } catch (exception: FirebaseAuthException) {
            SignOutResult.Error()
        }
    }

    override suspend fun createUser(fullName: String, email: String): UserResult =
        withContext(ioDispatcher) {
        try {
            var isRegisteredUser = false
            firebaseFirestore.collection(COLLECTION_USERS).add(
                hashMapOf(
                    FULL_NAME_FIELD to fullName,
                    EMAIL_FIELD to email
                )
            )
                .addOnSuccessListener { isRegisteredUser = true }
                .addOnFailureListener { isRegisteredUser = false }
            UserResult.Success(isRegistered = isRegisteredUser)
        } catch (exception: IOException) {
            UserResult.Error
        }
    }

    override suspend fun recoverPassword(email: String): Boolean {
        return false
    }

}