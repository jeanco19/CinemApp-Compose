package com.jean.cinemappcompose.auth.di

import com.jean.cinemappcompose.auth.data.datasource.AuthRemoteDataSource
import com.jean.cinemappcompose.auth.data.repository.AuthRepositoryImpl
import com.jean.cinemappcompose.auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryAuthModule {

    @Singleton
    @Provides
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource)
    }

}