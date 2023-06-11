package com.jean.cinemappcompose.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jean.cinemappcompose.data.datasource.remote.auth.AuthRemoteDataSource
import com.jean.cinemappcompose.data.datasource.remote.auth.AuthRemoteDataSourceImpl
import com.jean.cinemappcompose.data.datasource.remote.profile.ProfileRemoteDataSource
import com.jean.cinemappcompose.data.datasource.remote.profile.ProfileRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

 // ----- AUTH MODULE ----- //

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(firebaseAuth: FirebaseAuth): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(firebaseAuth)
    }

    // ----- PROFILE MODULE ----- //

    @Singleton
    @Provides
    fun provideProfileRemoteDataSource(firestore: FirebaseFirestore): ProfileRemoteDataSource {
        return ProfileRemoteDataSourceImpl(firestore)
    }

}