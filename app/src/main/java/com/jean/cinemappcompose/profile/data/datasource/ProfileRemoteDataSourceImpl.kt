package com.jean.cinemappcompose.profile.data.datasource

import com.google.firebase.firestore.CollectionReference
import com.jean.cinemappcompose.profile.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val userCollection: CollectionReference,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProfileRemoteDataSource {

    override suspend fun createUser(user: User) {
        withContext(ioDispatcher) {
            userCollection.document(user.id).set(user).await()
        }
    }

    override fun getUser(id: String): Flow<User> {
        return flow {
            val document = userCollection.document(id).get().await()
            val user = document.toObject(User::class.java)
            emit(user ?: User())
        }.flowOn(ioDispatcher)
    }

}