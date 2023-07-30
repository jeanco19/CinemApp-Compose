package com.jean.cinemappcompose.profile.di

import com.jean.cinemappcompose.profile.domain.repository.ProfileRepository
import com.jean.cinemappcompose.profile.domain.usecase.CreateUserUseCase
import com.jean.cinemappcompose.profile.domain.usecase.CreateUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseProfileModule {

    @Provides
    fun provideCreateUserUseCase(profileRepository: ProfileRepository): CreateUserUseCase {
        return CreateUserUseCaseImpl(profileRepository)
    }

}