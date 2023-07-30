package com.jean.cinemappcompose.auth.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkAuthModule {

    // ----- FIREBASE NETWORK ----- //

    @Singleton
    @Provides
    fun provideFirebase(): Firebase = Firebase

    @Singleton
    @Provides
    fun provideFirebaseAuth(firebase: Firebase): FirebaseAuth = firebase.auth

    @Singleton
    @Provides
    fun provideFirebaseFirestore(firebase: Firebase): FirebaseFirestore = firebase.firestore

}