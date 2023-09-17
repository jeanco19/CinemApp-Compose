package com.jean.cinemappcompose.profile.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.jean.cinemappcompose.core.util.Constants.COLLECTION_USERS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideUserCollection(firestore: FirebaseFirestore): CollectionReference {
        return firestore.collection(COLLECTION_USERS)
    }

}