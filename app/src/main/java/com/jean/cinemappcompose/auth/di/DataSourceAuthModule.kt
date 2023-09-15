package com.jean.cinemappcompose.auth.di

import com.google.firebase.auth.FirebaseAuth
import com.jean.cinemappcompose.auth.data.datasource.AuthRemoteDataSource
import com.jean.cinemappcompose.auth.data.datasource.AuthRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceAuthModule {

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(firebaseAuth: FirebaseAuth): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(firebaseAuth)
    }

}