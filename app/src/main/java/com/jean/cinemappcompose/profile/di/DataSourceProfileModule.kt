package com.jean.cinemappcompose.profile.di

import com.google.firebase.firestore.FirebaseFirestore
import com.jean.cinemappcompose.profile.data.datasource.ProfileRemoteDataSource
import com.jean.cinemappcompose.profile.data.datasource.ProfileRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceProfileModule {

    @Singleton
    @Provides
    fun provideProfileRemoteDataSource(firestore: FirebaseFirestore): ProfileRemoteDataSource {
        return ProfileRemoteDataSourceImpl(firestore)
    }

}