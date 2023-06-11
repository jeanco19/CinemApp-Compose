package com.jean.cinemappcompose.data.datasource.remote.profile

import com.google.firebase.firestore.FirebaseFirestore
import com.jean.cinemappcompose.core.Constants
import com.jean.cinemappcompose.core.Constants.COLLECTION_USERS
import com.jean.cinemappcompose.domain.model.profile.ProfileResult
import com.jean.cinemappcompose.domain.model.profile.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProfileRemoteDataSource {

    override suspend fun createUser(user: User): ProfileResult {
        return withContext(ioDispatcher) {
            try {
                firebaseFirestore.collection(COLLECTION_USERS).document(user.id).set(user).await()
                ProfileResult.Success(isRegistered = true)
            } catch (exception: IOException) {
                ProfileResult.Error
            }
        }
    }

}