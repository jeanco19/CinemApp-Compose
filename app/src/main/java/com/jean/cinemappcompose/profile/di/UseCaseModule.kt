package com.jean.cinemappcompose.profile.di

import com.jean.cinemappcompose.profile.domain.repository.ProfileRepository
import com.jean.cinemappcompose.profile.domain.usecase.CreateUserUseCase
import com.jean.cinemappcompose.profile.domain.usecase.CreateUserUseCaseImpl
import com.jean.cinemappcompose.profile.domain.usecase.GetCurrentUserIdUseCase
import com.jean.cinemappcompose.profile.domain.usecase.GetUserUseCase
import com.jean.cinemappcompose.profile.domain.usecase.GetUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideCreateUserUseCase(profileRepository: ProfileRepository): CreateUserUseCase {
        return CreateUserUseCaseImpl(profileRepository)
    }

    @Provides
    fun provideGetUserUseCase(
        profileRepository: ProfileRepository,
        getCurrentUserIdUseCase: GetCurrentUserIdUseCase
    ) : GetUserUseCase {
        return GetUserUseCaseImpl(profileRepository, getCurrentUserIdUseCase)
    }

}