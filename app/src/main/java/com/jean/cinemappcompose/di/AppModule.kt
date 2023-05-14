package com.jean.cinemappcompose.di

import com.jean.cinemappcompose.data.util.EmailValidator
import com.jean.cinemappcompose.domain.utils.EmailPatternValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideEmailValidator(): EmailPatternValidator {
        return EmailValidator()
    }

}