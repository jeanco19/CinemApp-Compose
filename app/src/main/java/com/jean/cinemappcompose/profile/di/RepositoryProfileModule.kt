package com.jean.cinemappcompose.profile.di

import com.jean.cinemappcompose.profile.data.datasource.ProfileRemoteDataSource
import com.jean.cinemappcompose.profile.data.repository.ProfileRepositoryImpl
import com.jean.cinemappcompose.profile.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryProfileModule {

    @Singleton
    @Provides
    fun provideProfileRepository(profileRemoteDataSource: ProfileRemoteDataSource): ProfileRepository {
        return ProfileRepositoryImpl(profileRemoteDataSource)
    }

}