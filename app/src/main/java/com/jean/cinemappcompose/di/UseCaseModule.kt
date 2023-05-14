package com.jean.cinemappcompose.di

import com.jean.cinemappcompose.domain.repository.auth.AuthRepository
import com.jean.cinemappcompose.domain.usecase.auth.*
import com.jean.cinemappcompose.domain.utils.EmailPatternValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    // ----- AUTH MODULE ----- //

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
    fun provideCreateUserUseCase(authRepository: AuthRepository): CreateUserUseCase {
        return CreateUserUseCaseImpl(authRepository)
    }

}