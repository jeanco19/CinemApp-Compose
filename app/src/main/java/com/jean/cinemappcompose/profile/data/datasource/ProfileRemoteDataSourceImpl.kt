package com.jean.cinemappcompose.profile.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.jean.cinemappcompose.core.util.Constants.COLLECTION_USERS
import com.jean.cinemappcompose.profile.domain.model.User
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

    override suspend fun createUser(user: User): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                firebaseFirestore.collection(COLLECTION_USERS).document(user.id).set(user).await()
                Result.success(Unit)
            } catch (exception: IOException) {
                Result.failure(exception)
            }
        }
    }

}