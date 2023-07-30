package com.jean.cinemappcompose.core.di

import com.jean.cinemappcompose.core.data.util.EmailPatternValidatorImpl
import com.jean.cinemappcompose.core.domain.util.EmailPatternValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppCoreModule {

    // ----- EMAIL PATTERN VALIDATOR ----- //

    @Provides
    fun provideEmailValidator(): EmailPatternValidator {
        return EmailPatternValidatorImpl()
    }

}