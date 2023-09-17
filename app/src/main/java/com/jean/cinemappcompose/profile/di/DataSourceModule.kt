package com.jean.cinemappcompose.profile.di

import com.google.firebase.firestore.CollectionReference
import com.jean.cinemappcompose.profile.data.datasource.ProfileRemoteDataSource
import com.jean.cinemappcompose.profile.data.datasource.ProfileRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideProfileRemoteDataSource(collection: CollectionReference): ProfileRemoteDataSource {
        return ProfileRemoteDataSourceImpl(collection)
    }

}