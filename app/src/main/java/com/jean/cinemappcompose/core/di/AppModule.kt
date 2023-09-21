package com.jean.cinemappcompose.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.jean.cinemappcompose.core.data.util.EmailPatternValidatorImpl
import com.jean.cinemappcompose.core.domain.util.EmailPatternValidator
import com.jean.cinemappcompose.core.util.connectivity.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ----- EMAIL PATTERN VALIDATOR ----- //

    @Provides
    fun provideEmailValidator(): EmailPatternValidator {
        return EmailPatternValidatorImpl()
    }

    // ----- CONNECTIVITY ----- //

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    fun provideNetworkConnectivityObserver(
        connectivityManager: ConnectivityManager
    ): NetworkConnectivityObserver {
        return NetworkConnectivityObserver(connectivityManager)
    }

}