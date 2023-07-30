package com.jean.cinemappcompose.auth.di

import com.jean.cinemappcompose.auth.domain.repository.AuthRepository
import com.jean.cinemappcompose.profile.domain.usecase.GetCurrentUserIdUseCase
import com.jean.cinemappcompose.profile.domain.usecase.GetCurrentUserIdUseCaseImpl
import com.jean.cinemappcompose.auth.domain.usecase.restart_password.RestartPasswordUseCase
import com.jean.cinemappcompose.auth.domain.usecase.restart_password.RestartPasswordUseCaseImpl
import com.jean.cinemappcompose.auth.domain.usecase.signin.SignInUseCase
import com.jean.cinemappcompose.auth.domain.usecase.signin.SignInUseCaseImpl
import com.jean.cinemappcompose.auth.domain.usecase.signout.SignOutUseCase
import com.jean.cinemappcompose.auth.domain.usecase.signout.SignOutUseCaseImpl
import com.jean.cinemappcompose.auth.domain.usecase.signup.SignUpUseCase
import com.jean.cinemappcompose.auth.domain.usecase.signup.SignUpUseCaseImpl
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCaseImpl
import com.jean.cinemappcompose.auth.domain.usecase.validator.PasswordValidatorUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.PasswordValidatorUseCaseImpl
import com.jean.cinemappcompose.core.domain.util.EmailPatternValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseAuthModule {

    @Provides
    fun provideGetCurrentUserId(authRepository: AuthRepository): GetCurrentUserIdUseCase {
        return GetCurrentUserIdUseCaseImpl(authRepository)
    }

    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase {
        return SignUpUseCaseImpl(authRepository)
    }

    @Provides
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase {
        return SignInUseCaseImpl(authRepository)
    }

    @Provides
    fun providesSignOutUseCase(authRepository: AuthRepository): SignOutUseCase {
        return SignOutUseCaseImpl(authRepository)
    }

    @Provides
    fun provideRestartPasswordUseCase(authRepository: AuthRepository): RestartPasswordUseCase {
        return RestartPasswordUseCaseImpl(authRepository)
    }

    @Provides
    fun emailValidatorUseCase(emailPatternValidator: EmailPatternValidator): EmailValidatorUseCase {
        return EmailValidatorUseCaseImpl(emailPatternValidator)
    }

    @Provides
    fun passwordValidatorUseCase(): PasswordValidatorUseCase {
        return PasswordValidatorUseCaseImpl()
    }

}