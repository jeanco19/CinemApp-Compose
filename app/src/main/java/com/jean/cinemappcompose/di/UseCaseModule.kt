package com.jean.cinemappcompose.di

import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import com.jean.cinemappcompose.domain.usecase.auth.*
import com.jean.cinemappcompose.core.EmailPatternValidator
import com.jean.cinemappcompose.domain.repository.profile.ProfileRepository
import com.jean.cinemappcompose.domain.usecase.profile.CreateUserUseCase
import com.jean.cinemappcompose.domain.usecase.profile.CreateUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

//---------- AUTH MODULE ----------//

    @Provides
    fun provideGetCurrentUserId(authRepository: AuthRepository): GetCurrentUserIdUseCase {
        return GetCurrentUserIdUseCaseImpl(authRepository)
    }

    @Provides
    fun provideSignUpUseCase(
        authRepository: AuthRepository,
        emailPatternValidator: EmailPatternValidator
    ): SignUpUseCase {
        return SignUpUseCaseImpl(authRepository, emailPatternValidator)
    }

    @Provides
    fun provideSignInUseCase(
        authRepository: AuthRepository,
        emailPatternValidator: EmailPatternValidator
    ): SignInUseCase {
        return SignInUseCaseImpl(authRepository, emailPatternValidator)
    }

    @Provides
    fun providesSignOutUseCase(authRepository: AuthRepository): SignOutUseCase {
        return SignOutUseCaseImpl(authRepository)
    }

    @Provides
    fun provideRestartPasswordUseCase(
        authRepository: AuthRepository,
        emailPatternValidator: EmailPatternValidator
    ): RestartPasswordUseCase {
        return RestartPasswordUseCaseImpl(authRepository, emailPatternValidator)
    }

//---------- PROFILE MODULE ----------//

    @Provides
    fun provideCreateUserUseCase(profileRepository: ProfileRepository): CreateUserUseCase {
        return CreateUserUseCaseImpl(profileRepository)
    }

}