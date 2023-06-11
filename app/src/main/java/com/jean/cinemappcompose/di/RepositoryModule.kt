package com.jean.cinemappcompose.di

import com.jean.cinemappcompose.data.datasource.remote.auth.AuthRemoteDataSource
import com.jean.cinemappcompose.data.datasource.remote.profile.ProfileRemoteDataSource
import com.jean.cinemappcompose.data.repository.auth.AuthRepositoryImpl
import com.jean.cinemappcompose.data.repository.profile.ProfileRepositoryImpl
import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import com.jean.cinemappcompose.domain.repository.profile.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // ----- AUTH MODULE ----- //

    @Singleton
    @Provides
    fun provideAuthRepository(authRemoteDataSource: AuthRemoteDataSource): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource)
    }

    // ----- PROFILE MODULE ----- //

    @Singleton
    @Provides
    fun provideProfileRepository(profileRemoteDataSource: ProfileRemoteDataSource): ProfileRepository {
        return ProfileRepositoryImpl(profileRemoteDataSource)
    }

}